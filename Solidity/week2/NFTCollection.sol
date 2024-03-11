// SPDX-License-Identifier: MIT
pragma solidity 0.8.22;

import {ERC721} from "@openzeppelin/contracts/token/ERC721/ERC721.sol";
import "@openzeppelin/contracts/access/Ownable.sol";

contract NFTCollection is ERC721, Ownable {
    constructor() ERC721("NFTCollection", "NFT") Ownable(msg.sender) {
        for (uint256 i = 1; i < 10; i += 1) {
            _safeMint(msg.sender, i);
        }
    }

    /**
     * You can implement a function to just approveAll, but you dont need to
     * Because ERC721 has implemented this for you, just use it.
     * Remember to approve() before you transfer() from contract
     */
    function approveAll(address operator) external onlyOwner {
        for (uint256 i = 1; i <= 10; i += 1) {
            _approve(operator, i, address(0));
        }
    }
}