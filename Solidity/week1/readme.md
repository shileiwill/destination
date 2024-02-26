ERC777: It defines advanced features to interact with tokens. Namely, operators to send tokens on behalf of another address—contract or regular account—and send/receive hooks to offer token holders more control over their tokens.
Within one transaction, you can call a function in a contract and also call the hook
it has reentrancy issue

ERC1363: 
Defines a token interface for ERC-20 tokens that supports executing recipient code after transfer or transferFrom, or spender code after approve.

Standard functions a token contract and contracts working with tokens can implement to make a token Payable.

- transferAndCall and transferFromAndCall will call an onTransferReceived on a ERC1363Receiver contract.

- approveAndCall will call an onApprovalReceived on a ERC1363Spender contract.

Motivation
There is no way to execute code after a ERC-20 transfer or approval (i.e. making a payment), so to make an action it is required to send another transaction and pay GAS twice.

This proposal wants to make token payments easier and working without the use of any other listener. It allows to make a callback after a transfer or approval in a single transaction.




It will revert instead of returning false.
SafeERC20 is a wrapper around the interface that eliminates the need to handle boolean return values. 
Wrappers around ERC20 operations that throw on failure (when the token contract returns false). Tokens that return no value (and instead revert or throw on failure) are also supported, non-reverting calls are assumed to be successful. To use this library you can add a using SafeERC20 for ERC20; statement to your contract, which allows you to call the safe operations as token.safeTransfer(…​), etc.
