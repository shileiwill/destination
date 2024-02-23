// SPDX-License-Identifier: MIT
pragma solidity ^0.8.20;

import "@openzeppelin/contracts/utils/Strings.sol";
import {OutcoToken} from "./OutcoToken.sol";

/**
1. Purchase NFT with ERC20 tokens

Bulld a classic NFT that can only be minted by paying with a particular ERC20 token.
*/

contract SimpleNFT {
    using Strings for uint256;

    mapping(uint256 => address) private _owners;
    string baseURL = "http://example.com/images/";

    // what if we have to pay OutcoToken in order to mint?
    address public erc20TokenAddress; // not needed
    OutcoToken public outcoToken;

    constructor(address _erc20TokenAddress) {
        erc20TokenAddress = _erc20TokenAddress;
        outcoToken = OutcoToken(_erc20TokenAddress);
    }

    function mint(uint256 _tokenId) external {
        require(_owners[_tokenId] == address(0), "this token is already minted");
        require(_tokenId < 100, "_tokenId too large");

        // Ensure that msg.sender has paid 1 OutcoToken
        require(outcoToken.transferFrom(msg.sender, address(this), 1), "Failed to transfer 1 OutcoToken for minting");

        _owners[_tokenId] = msg.sender;
    }

    function ownerOf(uint256 _tokenId) external view returns (address) {
        require(_owners[_tokenId] != address(0), "no such token");
        return _owners[_tokenId];
    }

    function transferFrom(address _from, address _to, uint256 _tokenId) external payable {
        require(_owners[_tokenId] != address(0), "token doesnt exist");
        require(_owners[_tokenId] == _from, "only owner can transfer");
        require(msg.sender == _from, "required to be owner");

        _owners[_tokenId] = _to;
    }

    function tokenURI(uint256 _tokenId) external view returns (string memory) {
        require(_owners[_tokenId] != address(0), "doesnt have this token");
        // because solidity doesnt support concat natively
        // if you just do string(abi.encodePacked(baseURL, _tokenId)), the tokenId will not be included
        // bc we are packing string and uint, which is not allowed
        return string(abi.encodePacked(baseURL, _tokenId.toString(), ".jpeg"));
    }

}