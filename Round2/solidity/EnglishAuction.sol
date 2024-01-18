// SPDX-License-Identifier: MIT
pragma solidity ^0.8.20;

/**
5. English auction contract

A seller calls deposit() to deposit an NFT into a contract along with a deadline and a reserve price. 
Buyers can bid on that NFT up until the deadline, and the highest bid wins. 
If the reserve price is not met, the NFT is not sold. Multiple auctions can happen at the same time. 
Buyers who did not win can withdraw their bid. The winner is not able to withdraw their bid and must complete the trade to buy the NFT. 
The seller can also end the auction by calling sellerEndAuction() which only works after expiration, 
and if the reserve is met. The winner will be transferred the NFT and the seller will receive the Ethereum.
*/

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

    // 0xE958D39c97216b45b46dC45c846931F12E99D78F, 1, 10, 1705520567
    function deposit(address nftAddress, uint256 tokenId, uint256 targetPrice, uint256 deadline) external {
        require(targetPrice > 0, "targetPrice must be greater than 0");
        require(deadline > block.timestamp, "Deadline must be in the future");
        require(IERC721(nftAddress).ownerOf(tokenId) == msg.sender, "Only the owner can list the product");

        // list the product
        bytes32 productId = getProductId(nftAddress, tokenId);
        
        Product storage aProd = products[productId];
        aProd.seller = msg.sender;
        aProd.nftAddress = nftAddress;
        aProd.tokenId = tokenId;
        aProd.targetPrice = targetPrice;
        aProd.deadline = deadline;
    }

    function getProductId(
        address nftAddress,
        uint256 tokenId
    ) public pure returns (bytes32) {
        return keccak256(abi.encodePacked(nftAddress, tokenId));
    }
}