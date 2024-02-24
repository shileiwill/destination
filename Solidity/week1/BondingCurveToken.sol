// SPDX-License-Identifier: MIT
pragma solidity ^0.8.0;

import "@openzeppelin/contracts/token/ERC20/ERC20.sol";

contract BondingCurveToken is ERC20 {

    uint256 public constant basePrice = 1 ether;
    uint256 public constant incrementPerToken = 0.1 ether; // first token is 1.1
    uint256 public lastPrice = 1 ether; // keep updating this number

    constructor() ERC20("Bonding Curve Token", "Bonding") {
        
    }

    function calculatePurchasePrice(uint256 amount) public view returns (uint256) {
        // token 1 -> token (amount)
        uint256 firstPurchaseTokenPrice = lastPrice + incrementPerToken;
        uint256 lastPurchaseTokenPrice = firstPurchaseTokenPrice + (amount - 1) * incrementPerToken;

        uint256 totalPrice = (firstPurchaseTokenPrice + lastPurchaseTokenPrice) * amount / 2;

        return totalPrice;
    } 

    function calculateSellPrice(uint256 amount) public view returns (uint256) {
        uint256 firstSellTokenPrice = lastPrice;
        uint256 lastSellTokenPrice = firstSellTokenPrice - (amount - 1) * incrementPerToken;

        uint256 totalPrice = (firstSellTokenPrice + lastSellTokenPrice) * amount / 2;

        return totalPrice;
    } 


    function purchase(uint256 amount) external payable {
        require(msg.value == calculatePurchasePrice(amount), "not enough ether to pay");
        _mint(msg.sender, amount);

        // update lastPrice
        lastPrice = lastPrice + amount * incrementPerToken;
    }

    function sell(uint256 amount) external {
        require(amount > 0, "amount must be greater than 0");
        uint256 paymentToSeller = calculateSellPrice(amount);

        _burn(msg.sender, amount);
        payable(msg.sender).transfer(paymentToSeller);

        lastPrice = lastPrice - amount * incrementPerToken;
    }
}

// Consider the case someone might sandwhich attack a bonding curve. What can you do about it? seller only after certain time