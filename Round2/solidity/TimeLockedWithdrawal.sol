// SPDX-License-Identifier: MIT
pragma solidity ^0.8.20;

import {OutcoToken} from "./OutcoToken.sol";

/**
2. Time unlocked ERC20 / vesting contract

A payer deposits a certain amount of tokens into a contract, but the receiver can only withdraw 1/n tokens over the course of n days.
*/

contract TimeLockedWithdrawal {
    address public payer;
    address public receiver;
    address public tokenAddress;
    uint public totalTokens;
    uint public releaseDuration; // in days
    uint public startTime;
    uint public lastReleaseTime;

    modifier onlyPayer() {
        require(msg.sender == payer, "Only the payer can call this function");
        _;
    }

    modifier onlyReceiver() {
        require(msg.sender == receiver, "Only the receiver can call this function");
        _;
    }

    event TokensDeposited(address payer, uint amount);
    event TokensWithdrawn(address receiver, uint amount);

    // 0x5B38Da6a701c568545dCfcB03FcB875f56beddC4, 0xAb8483F64d9C6d1EcF9b849Ae677dD3315835cb2, 0x5FD6eB55D12E759a21C09eF703fe0CBa1DC9d88D, 100, 5

    constructor(
        address _payer,
        address _receiver,
        address _tokenAddress,
        uint _totalTokens,
        uint _releaseDurationInDays
    ) {
        require(_payer != address(0), "Invalid payer address");
        require(_receiver != address(0), "Invalid receiver address");
        require(_totalTokens > 0, "Total tokens must be greater than 0");
        require(_releaseDurationInDays > 0, "Release interval must be greater than 0 days");

        payer = _payer;
        receiver = _receiver;
        totalTokens = _totalTokens;
        releaseDuration = _releaseDurationInDays;
        tokenAddress = _tokenAddress;

        startTime = block.timestamp;
        lastReleaseTime = startTime;

        // transfer token to the contract
        require(OutcoToken(_tokenAddress).transferFrom(payer, address(this), totalTokens), "initial deposit failed");
        emit TokensDeposited(payer, totalTokens);
    }

    // check if it has passed 1 day
    function isTokenAvailable() public view returns (bool) {
        uint timeElapsed = block.timestamp - lastReleaseTime;
        // all in seconds. (1 days) is 1 day in seconds
        // uint periodsPassed = timeElapsed / (1 days);
        uint periodsPassed = timeElapsed / (2 minutes);

        return periodsPassed > 0;
    }

    function withdrawTokens() external onlyReceiver {
        require(isTokenAvailable(), "no tokens available for withdrawal");
        uint tokensToWithdraw = totalTokens / releaseDuration;
        
        // transfer token to the receiver
        require(OutcoToken(tokenAddress).transferFrom(address(this), receiver, tokensToWithdraw), "token withdrawal failed");
        lastReleaseTime = block.timestamp;
        emit TokensWithdrawn(receiver, tokensToWithdraw);
    }
}