// SPDX-License-Identifier: MIT
pragma solidity ^0.8.20;

/**
4. Crowdfunding ERC20 contract

Your contract should have a createFundraiser() function with a goal and a deadline as arguments. Donators can donate() to a given fundraiserId. If the goal is reached before the deadline, the wallet that called createFundraiser() Can withdraw() all the funds associated with that campaign. Otherwise, if the deadline passes without reaching the goal, the donators can withdraw their donation. Build a contract that supports Ether and another that supports ERC20 tokens.

Some corner cases to think about:

    what if the same address donates multiple times?

    what if the same address donates to multiple different campaigns?
*/

contract CrowdFundingEther {
    struct Fundraiser {
        address creator;
        uint256 goal;
        uint256 deadline;
        uint256 totalFunds;
        mapping(address => uint256) donations;
    }

    mapping(uint256 => Fundraiser) public fundraisers;
    uint256 public nextFundraiserId;

    function createFundraiser(uint256 goal, uint256 deadline) external {
        require(goal > 0, "Goal must be greater than 0");
        require(deadline > block.timestamp, "Deadline must be in the future");

        // fundraisers[nextFundraiserId] = Fundraiser(
        //     {
        //         creator: msg.sender,
        //         goal: goal,
        //         deadline: deadline,
        //         totalFunds: 0
        //     }
        // );
        Fundraiser storage newFundraiser = fundraisers[nextFundraiserId];
        newFundraiser.creator = msg.sender;
        newFundraiser.goal = goal;
        newFundraiser.deadline = deadline;
        newFundraiser.totalFunds = 0;

        nextFundraiserId++;
    }

    function donate(uint256 fundraiserId) external payable {
        Fundraiser storage fundraiser = fundraisers[fundraiserId];
        require(msg.value > 0, "Donation must be greater than 0");
        require(block.timestamp < fundraiser.deadline, "Fundraising expired");

        fundraiser.donations[msg.sender] += msg.value;
        fundraiser.totalFunds += msg.value;
    }

    function withdraw(uint256 fundraiserId) external {
        Fundraiser storage fundraiser = fundraisers[fundraiserId];
        require(block.timestamp > fundraiser.deadline, "fundraising is still active");
        
        if (fundraiser.totalFunds >= fundraiser.goal && fundraiser.creator == msg.sender) {
            // creator withdrawing all
            payable(msg.sender).transfer(fundraiser.totalFunds);
        } else if (fundraiser.totalFunds < fundraiser.goal) {
            // failed campaign, donors can withdraw their donations
            uint256 donationAmount = fundraiser.donations[msg.sender];
            require(donationAmount > 0, "You should have donated something to withdraw");

            fundraiser.donations[msg.sender] = 0;
            payable(msg.sender).transfer(donationAmount);
        }
    }
}