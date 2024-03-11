// SPDX-License-Identifier: MIT
pragma solidity ^0.8.0;

import "@openzeppelin/contracts/token/ERC20/ERC20.sol";
import "@openzeppelin/contracts/utils/math/Math.sol";



contract BondingCurveToken is ERC20 {


    uint256 public constant basePrice = 10 ether;
    uint256 public constant incrementPerToken = 1 ether; // first token is 11
    uint256 public lastPrice = 10 ether; // keep updating this number

    constructor() ERC20("Bonding Curve Token", "Bonding") {
        
    }

    function _calculatePurchasePrice(uint256 amount) internal view returns (uint256) {
        // token 1 -> token (amount)
        uint256 firstPurchaseTokenPrice = lastPrice + incrementPerToken;
        uint256 lastPurchaseTokenPrice = firstPurchaseTokenPrice + (amount - 1) * incrementPerToken;

        // uint256 totalPrice = (firstPurchaseTokenPrice + lastPurchaseTokenPrice) * amount / 2;
        // purchase price should round up, extreme case is this could be 0
        uint256 totalPrice = Math.ceilDiv((firstPurchaseTokenPrice + lastPurchaseTokenPrice) * amount, 2);


        return totalPrice;
    } 

    function _calculateSellPrice(uint256 amount) internal view returns (uint256) {
        uint256 firstSellTokenPrice = lastPrice;
        uint256 lastSellTokenPrice = firstSellTokenPrice - (amount - 1) * incrementPerToken;

        // sell price round down
        uint256 totalPrice = (firstSellTokenPrice + lastSellTokenPrice) * amount / 2;

        return totalPrice;
    } 


    function purchase(uint256 amount) external payable {
        require(msg.value == _calculatePurchasePrice(amount), "not the right amount of ether to pay");
        _mint(msg.sender, amount);

        // update lastPrice
        lastPrice = lastPrice + amount * incrementPerToken;
    }

    function sell(uint256 amount) external {
        require(amount > 0, "amount must be greater than 0");
        uint256 paymentToSeller = _calculateSellPrice(amount);

        _burn(msg.sender, amount);
        // stop using solidity transfer bc it forwards only 2300 gas: https://consensys.io/diligence/blog/2019/09/stop-using-soliditys-transfer-now/
        // payable(msg.sender).transfer(paymentToSeller);
        
        // This forwards all available gas. Be sure to check the return value!
        (bool success, ) = msg.sender.call{value: paymentToSeller}("");
        require(success, "Transfer failed.");
        

        lastPrice = lastPrice - amount * incrementPerToken;
    }
}