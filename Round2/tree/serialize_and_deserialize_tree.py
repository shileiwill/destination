serialize_and_deserialize_tree.py

# Definition for a binary tree node.
# class TreeNode(object):
#     def __init__(self, x):
#         self.val = x
#         self.left = None
#         self.right = None

# iteration using BFS: https://leetcode.com/problems/serialize-and-deserialize-bst/solutions/3051832/python-solution/ 

# A tree problem using both DFS and BFS. In DFS, we talk about preorder, inorder and postorder

class Codec:

    def serializeDFS(self, root):
        """Encodes a tree to a single string.
        
        :type root: TreeNode
        :rtype: str
        """

        def helper(root, preorder):
            if root is None:
                preorder[0] = preorder[0] + "#,"
                return
            preorder[0] = preorder[0] + str(root.val) + ","
            helper(root.left, preorder)
            helper(root.right, preorder)

        # if you pass in just the string, you can't capture the change
        preorder = [""]
        helper(root, preorder)
        return preorder[0]

    def deserializeWithUpdadingArray(self, data):
        """Decodes your encoded data to tree.
        
        :type data: str
        :rtype: TreeNode
        """
        arr = data[0:-1].split(",")
        def helper(arr):
            if arr[0] == "#":
                arr.pop(0)
                return None
            root = TreeNode(int(arr[0]))
            arr.pop(0)
            root.left = helper(arr)
            root.right = helper(arr)
            return root 
        
        return helper(arr)

    def deserializeWithIndex(self, data):
        """Decodes your encoded data to tree.
        using index, but wrapped in an array.
        not changing the original array
        :type data: str
        :rtype: TreeNode
        """
        arr = data[0:-1].split(",")
        def helper(arr, index):
            if arr[index[0]] == "#":
                return None
            root = TreeNode(int(arr[index[0]]))
            # arr.pop(0)
            index[0] += 1
            root.left = helper(arr, index)
            index[0] += 1
            root.right = helper(arr, index)
            return root 
        
        return helper(arr, [0])

    def serialize(self, root):
        """Encodes a tree to a single string.
        use BFS, level order traversal
        :type root: TreeNode
        :rtype: str
        """
        if not root:
            return ""

        queue = []
        res = ""
        queue.append(root)
        while len(queue) > 0:
            # if no index, it defaults to -1, which is the latest added item(stack) 
            node = queue.pop(0)
            if node is None:
                res += "None,"
            else:
                res += str(node.val) + ","
                queue.append(node.left)
                queue.append(node.right)
        return res

    def deserialize(self, data):
        """Decodes your encoded data to tree.
        BFS 
        :type data: str
        :rtype: TreeNode
        """
        if not data:
            return None 
        arr = data[0:-1].split(",")
        root = TreeNode(int(arr[0]))
        index = 1 # this index is moving
        queue = [root]
        while queue:
            node = queue.pop(0)
            
            if arr[index] != "None":
                node.left = TreeNode(int(arr[index]))
                queue.append(node.left) 
            index += 1

            if arr[index] != "None":
                node.right = TreeNode(int(arr[index]))
                queue.append(node.right)
            index += 1 

        return root 

# Your Codec object will be instantiated and called as such:
# ser = Codec()
# deser = Codec()
# ans = deser.deserialize(ser.serialize(root))