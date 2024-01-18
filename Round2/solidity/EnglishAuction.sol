// SPDX-License-Identifier: MIT
pragma solidity ^0.8.20;

interface IERC721 {
    function ownerOf(uint256 tokenId) external view returns (address owner);
    function setApprovalForAll(address _operator, bool _approved) external;
    function transferFrom(address _from, address _to, uint256 _tokenId) external payable;
}

contract EnglishAuction {
    struct Product {
        address seller;
        address nftAddress;
        uint256 tokenId;
        uint256 targetPrice;
        uint256 deadline;
        address highestBidder;
        uint256 highestBid;
        bool ended;
        mapping(address => uint256) bids;
        address[] bidders;
    }

    mapping(bytes32 => Product) public products;

    event AuctionCreated(address nftAddress, uint256 tokenId, uint256 targetPrice, uint256 deadline);
    event AuctionEnded(address nftAddress, uint256 tokenId);
    event BidPlaced(bytes32 productId, address bidder, uint256 bidAmount);
    event BidWithdrawn(bytes32 productId, address bidder);

    // 0xd8b934580fcE35a11B58C6D73aDeE468a2833fa8, 1, 10, 1705620636
    function deposit(address nftAddress, uint256 tokenId, uint256 targetPrice, uint256 deadline) external {
        require(targetPrice > 0, "targetPrice must be greater than 0");
        require(deadline > block.timestamp, "Deadline must be in the future");
        require(IERC721(nftAddress).ownerOf(tokenId) == msg.sender, "Only the owner can list the product");

        // transfer the nft to the contract
        IERC721(nftAddress).transferFrom(msg.sender, address(this), tokenId);

        // list the product
        bytes32 productId = getProductId(nftAddress, tokenId);

        Product storage aProd = products[productId];
        aProd.seller = msg.sender;
        aProd.nftAddress = nftAddress;
        aProd.tokenId = tokenId;
        aProd.targetPrice = targetPrice;
        aProd.deadline = deadline;

        emit AuctionCreated(nftAddress, tokenId, targetPrice, deadline);
    }

    function placeBid(address nftAddress, uint256 tokenId) external payable {
        bytes32 productId = getProductId(nftAddress, tokenId);
        Product storage aProd = products[productId];

        require(aProd.ended == false, "auction already ended");
        require(msg.value > 0, "bid price must be bigger than 0");
        
        if (aProd.highestBidder == address(0) || msg.value >= aProd.highestBid) {
            // only update when first bid for the auction or the highest bid so far
            aProd.highestBid = msg.value;
            aProd.highestBidder = msg.sender;
        }

        // highest or not, we need to update bids and bidders
        aProd.bids[msg.sender] = msg.value;
        aProd.bidders.push(msg.sender);

        emit BidPlaced(productId, msg.sender, msg.value);
    }

    function withdrawBid(address nftAddress, uint256 tokenId) external {
        bytes32 productId = getProductId(nftAddress, tokenId);
        Product storage aProd = products[productId];

        require(aProd.ended == false, "auction already ended, can not withdraw");
        require(msg.sender != aProd.highestBidder, "current winning bidder can not withdraw");

        uint256 amount = aProd.bids[msg.sender];
        require(amount > 0, "only participants can withdraw");

        aProd.bids[msg.sender] = 0;
        // remove from bidders
        for (uint256 i = 0; i < aProd.bidders.length; i++) {
            if (aProd.bidders[i] == msg.sender) {
                address lastBidder = aProd.bidders[aProd.bidders.length - 1];
                aProd.bidders[i] = lastBidder;
                aProd.bidders.pop();
                break;
            }
        }

        payable(msg.sender).transfer(amount);
        emit BidWithdrawn(productId, msg.sender);
    }

    function sellerEndAuction(address nftAddress, uint256 tokenId) external {
        bytes32 productId = getProductId(nftAddress, tokenId);
        Product storage aProd = products[productId];

        require(block.timestamp > aProd.deadline, "can't end before the deadline");
        require(msg.sender == aProd.seller, "only the seller can end");
        require(aProd.ended == false, "auction already ended");

        aProd.ended = true;

        // if there is no bid at all, just end
        if (aProd.highestBidder != address(0)) {
            if (aProd.highestBid >= aProd.targetPrice) {
                // auction succeeded
                // transfer the highest bid amount to seller
                payable(aProd.highestBidder).transfer(aProd.highestBid);
                // transfer the nft to highest bidder
                IERC721(aProd.nftAddress).transferFrom(address(this), aProd.highestBidder, aProd.tokenId);
            } 

            for (uint256 i = 0; i < aProd.bidders.length; i++) {
                address bidder = aProd.bidders[i];
                if (bidder == aProd.highestBidder) {
                    continue;
                }
                uint256 amount = aProd.bids[bidder];
                payable(bidder).transfer(amount);
            }
        }

        emit AuctionEnded(nftAddress, tokenId);
    }

    function getProductId(address nftAddress, uint256 tokenId) public pure returns (bytes32) {
        return keccak256(abi.encodePacked(nftAddress, tokenId));
    }
}