// SPDX-License-Identifier: MIT
pragma solidity ^0.8.20;

import "@openzeppelin/contracts/utils/Strings.sol";
import {OutcoToken} from "./OutcoToken.sol";

/**
Added the functionality of setApproval so Opensea can transfer on the owner's behalf
*/

contract AdvancedNFT {
    using Strings for uint256;

    mapping(uint256 => address) private _owners;
    // msg.sender: {operator:true}
    mapping(address => mapping(address => bool)) private _operators;

    string baseURL = "http://example.com/images/";

    // what if we have to pay OutcoToken in order to mint?
    address public erc20TokenAddress; // not needed
    OutcoToken public outcoToken;

    event Transfer(address indexed _from, address indexed _to, uint256 indexed _tokenId);
    event ApprovalForAll(address indexed _owner, address indexed _operator, bool _approved);

    constructor(address _erc20TokenAddress) {
        erc20TokenAddress = _erc20TokenAddress;
        outcoToken = OutcoToken(_erc20TokenAddress);
    }

    function mint(uint256 _tokenId) external {
        require(_owners[_tokenId] == address(0), "this token is already minted");
        require(_tokenId < 100, "_tokenId too large");

        // Ensure that msg.sender has paid 1 OutcoToken
        // require(outcoToken.transferFrom(msg.sender, address(this), 1), "Failed to transfer 1 OutcoToken for minting");

        emit Transfer(address(0), msg.sender, _tokenId);
        _owners[_tokenId] = msg.sender;
    }

    function ownerOf(uint256 _tokenId) external view returns (address) {
        require(_owners[_tokenId] != address(0), "no such token");
        return _owners[_tokenId];
    }

    function transferFrom(address _from, address _to, uint256 _tokenId) external payable {
        require(_owners[_tokenId] != address(0), "token doesnt exist");
        require(_owners[_tokenId] == _from, "only owner can transfer");
        require(msg.sender == _from || _operators[_from][msg.sender], "required to be owner or approved 3rd party");

        // after the NFT is transferred, we need to reset the approval since the NFT has changed owner.
        _operators[_from][msg.sender] = false;
        emit Transfer(_from, _to, _tokenId);
        _owners[_tokenId] = _to;
    }

    function tokenURI(uint256 _tokenId) external view returns (string memory) {
        require(_owners[_tokenId] != address(0), "doesnt have this token");
        // because solidity doesnt support concat natively
        // if you just do string(abi.encodePacked(baseURL, _tokenId)), the tokenId will not be included
        // bc we are packing string and uint, which is not allowed
        return string(abi.encodePacked(baseURL, _tokenId.toString(), ".jpeg"));
    }

    function setApprovalForAll(address _operator, bool _approved) external {
        _operators[msg.sender][_operator] = _approved;
        emit ApprovalForAll(msg.sender, _operator, _approved);
    }
}