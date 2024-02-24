// SPDX-License-Identifier: MIT
pragma solidity ^0.8.20;

import "@openzeppelin/contracts/token/ERC20/IERC20.sol";
import "@openzeppelin/contracts/utils/math/SafeMath.sol";
import "@openzeppelin/contracts/token/ERC20/ERC20.sol";

// contract EscrowToken is ERC20 {

//     constructor() ERC20("Escrow Token", "Escrow") {}

//       function mint(uint amount) public {
//         _mint(msg.sender, amount);
//       }
// }
// buyer: 0x5B38Da6a701c568545dCfcB03FcB875f56beddC4
// seller: 0xAb8483F64d9C6d1EcF9b849Ae677dD3315835cb2, 
// tokenAddr: 0xd9145CCE52D386f254917e481eB44e9943F39138

// 0xAb8483F64d9C6d1EcF9b849Ae677dD3315835cb2, 0xd9145CCE52D386f254917e481eB44e9943F39138, 4
// 
contract TokenEscrow {
    using SafeMath for uint256;

    struct Deposit {
        address buyer;
        address seller;
        address tokenAddress;
        uint256 amount;
        uint256 releaseTime;
        bool released;
    }

    mapping(uint256 => Deposit) public deposits;
    uint256 public nextDepositId;

    bool public locked;
    modifier nonReentrant() {
    	require(!locked, "no reentrancy");
    	locked = true;
    	_;
    	locked = false;
    }

    function deposit(address _seller, address _tokenAddress, uint256 amount) external returns (uint256) {
        require(amount > 0, "Deposit must be greater than 0");

        IERC20 token = IERC20(_tokenAddress);
        require(token.transferFrom(msg.sender, address(this), amount), "transfer from buyer to escrow failed");

        uint256 releaseTime = block.timestamp + 3 days;
        deposits[nextDepositId] = Deposit(msg.sender, _seller, _tokenAddress, amount, releaseTime, false);

        nextDepositId++;

        return nextDepositId - 1;
    }

    // non-reentrant modifier
    function withdraw(uint256 depositId) external nonReentrant {
        Deposit storage depositInfo = deposits[depositId];
        require(depositInfo.seller == msg.sender, "only seller can withdraw");
        require(block.timestamp > depositInfo.releaseTime, "only withdraw after 3 days");
        require(!depositInfo.released, "already withdrawn");

        IERC20 token = IERC20(depositInfo.tokenAddress);
        require(token.balanceOf(address(this)) >= depositInfo.amount, "escrow doesnt have enough money");

        require(token.transferFrom(address(this), depositInfo.seller, depositInfo.amount), "Token transfer to seller failed");
        depositInfo.released = true;
    }
}

// non-reentrant for withdraw function
// This doesnt support non-ERC20 tokens