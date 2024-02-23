// SPDX-License-Identifier: MIT
pragma solidity ^0.8.20;

/**
6. Simple NFT Marketplace
Sellers can sell() their NFT while specifying a price and expiration. Instead of depositing the NFT into the contract, they give the contract approval to withdraw it from them. If a buyer comes along and pays the specified price before the expiration, then the NFT is transferred from the seller to the buyer and the buyer’s ether is transferred to the seller.

The seller can cancel() the sale at any time.

Corner cases:

    What if the seller lists the same NFT twice? This can theoretically happen since they don't transfer the NFT to the marketplace.
*/

interface IERC721 {
    function ownerOf(uint256 tokenId) external view returns (address owner);
    function setApprovalForAll(address _operator, bool _approved) external;
    function transferFrom(address _from, address _to, uint256 _tokenId) external payable;
}

contract NFTMarketPlace {
    struct Product {
        address seller;
        address nftAddress;
        uint256 tokenId;
        uint256 targetPrice;
        uint256 deadline;
    }

    mapping(bytes32 => Product) public products;

    // 0xE958D39c97216b45b46dC45c846931F12E99D78F, 1, 10, 1705520567
    function sell(address nftAddress, uint256 tokenId, uint256 targetPrice, uint256 deadline) external {
        require(targetPrice > 0, "targetPrice must be greater than 0");
        require(deadline > block.timestamp, "Deadline must be in the future");
        require(IERC721(nftAddress).ownerOf(tokenId) == msg.sender, "Only the owner can list the product");

        // list the product
        bytes32 productId = getProductId(nftAddress, tokenId);
        products[productId] = Product(
            {
                seller: msg.sender,
                nftAddress: nftAddress,
                tokenId: tokenId,
                targetPrice: targetPrice,
                deadline: deadline
            }
        );

        // give marketplace approval
        IERC721(nftAddress).setApprovalForAll(address(this), true);
    }

    function buy(address nftAddress, uint256 tokenId) external payable {
        bytes32 productId = getProductId(nftAddress, tokenId);
        Product storage aProd = products[productId];

        require(msg.value >= aProd.targetPrice, "purchase price must be greater than target price");
        require(aProd.deadline > block.timestamp, "Listing passed deadline");

        // transfer the nft to buyer
        IERC721(nftAddress).transferFrom(aProd.seller, msg.sender, tokenId);

        // transfer ether to seller
        payable(aProd.seller).transfer(msg.value);

        // delete listing
        delete products[productId];
    }

    function cancel(address nftAddress, uint256 tokenId) external {
        bytes32 productId = getProductId(nftAddress, tokenId);
        Product storage aProd = products[productId];

        require(aProd.seller == msg.sender, "only seller can cancel");
        delete products[productId];
    }

    function getProductId(
        address nftAddress,
        uint256 tokenId
    ) public pure returns (bytes32) {
        return keccak256(abi.encodePacked(nftAddress, tokenId));
    }
}