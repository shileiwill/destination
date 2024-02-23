// SPDX-License-Identifier: MIT
pragma solidity ^0.8.20;

import {SimpleNFT} from "./SimpleNFT.sol";

/**
3. NFT Swap Contract

Two people want to trade their NFTs in a trustless way. A user creates a swap on the contract, which is a pair of address, ids where the address is the smart contract address of the NFT and the id is the tokenId of the NFT. One person can deposit an NFT only if the id matches the address and id. The counterparty can deposit only if their NFT matches the address and id of the swap.

Once both are deposited, either party can call swap.

Some corner cases to think about:

    how long, if at all, should the traders be forced to keep their NFT in the contract?
*/


contract NFTSwap {
    address public nftAddress;

    struct Swap {
        address partyA;
        uint256 idA;
        address partyB;
        uint256 idB;
        bool partyADeposited;
        bool partyBDeposited;
    }

    mapping(bytes32 => Swap) public swaps;

    modifier onlyParty(bytes32 swapId, bool isPartyA) {
        Swap storage swap = swaps[swapId];
        require(msg.sender == (isPartyA ? swap.partyA : swap.partyB), "Only relevant parties are allowed");
        _;
    }

    constructor(
        address _nftAddress
    ) {
        nftAddress = _nftAddress;
    }

    function getSwapId(
        address partyA,
        uint256 idA,
        address partyB,
        uint idB
    ) public pure returns (bytes32) {
        return keccak256(abi.encodePacked(partyA, idA, partyB, idB));
    }

    // partyA is the user wallet address
    // let's assume the NFT is always the SimpleNFT contract
    // anybody can create a swap for anybody
    function createSwap(
        address partyA,
        uint256 idA,
        address partyB,
        uint idB
    ) external {
        bytes32 swapId = getSwapId(partyA, idA, partyB, idB);
        require(swaps[swapId].partyA == address(0) && swaps[swapId].partyB == address(0), "One swap at a time");

        swaps[swapId] = Swap(partyA, idA, partyB, idB, false, false);
    }

    function depositNFT(bytes32 swapId) external {
        Swap storage swap = swaps[swapId];
        require(swap.partyA == msg.sender || swap.partyB == msg.sender, "Only relevant parties can deposit");

        // address aParty = (msg.sender == swap.partyA) ? swap.partyA : swap.partyB;
        uint256 nftId = (msg.sender == swap.partyA) ? swap.idA : swap.idB;

        SimpleNFT(nftAddress).transferFrom(msg.sender, address(this), nftId);

        if (msg.sender == swap.partyA) {
            swap.partyADeposited = true;
        } else {
            swap.partyBDeposited = true;
        }

        if (swap.partyADeposited && swap.partyBDeposited) {
            // both deposited, swap
            swapNFTs(swapId);
        }
    }

    function swapNFTs(bytes32 swapId) public onlyParty(swapId, true) {
        Swap storage swap = swaps[swapId];
        require(swap.partyADeposited && swap.partyBDeposited, "Both parties should have deposited");

        SimpleNFT(nftAddress).transferFrom(address(this), swap.partyB, swap.idA);
        SimpleNFT(nftAddress).transferFrom(address(this), swap.partyA, swap.idB);

        delete swaps[swapId];
    } 
}