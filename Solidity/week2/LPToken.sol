// SPDX-License-Identifier: MIT
pragma solidity 0.8.24;

import {ERC20} from "@openzeppelin/contracts/token/ERC20/ERC20.sol";
import {Ownable} from "@openzeppelin/contracts/access/Ownable.sol";

/**
 1. ERC20 or IERC20 doesnt cover the mint function, so you need to implement that.
 2. You can transferOwnership to a new non-zero address
*/
contract LPToken is ERC20, Ownable {
    constructor() ERC20("LPToken", "LP") Ownable(msg.sender) {}

    function mint(address to, uint256 amount) public onlyOwner {
        _mint(to, amount);
    }
}