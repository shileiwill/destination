findTargetInGraphTree.java

public List<List<Integer>> findTarget(TreeNode root) {
        if (root == null) {
            return null;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        Map<TreeNode, TreeNode> map = new HashMap<>();
        boolean isFound = false;

        while (!queue.isEmpty() && !isFound) {
            TreeNode cur = queue.poll();
            
            for (TreeNode next : cur.children) {
                map.put(next, cur);
                if (next == target) {
                    isFound = true;
                    break; //if there are multiple paths, shouldn't stop
                }
                if (!map.containsKey(next)) {
                    queue.offer(next);
                }
                
            }
        }

        return getPath(map);
    }

    // If more than 1 path
    public List<List<Integer>> findTarget(TreeNode root) {
        if (root == null) {
            return null;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        Map<TreeNode, Set<TreeNode>> map = new HashMap<>();

        while (!queue.isEmpty()) {
            TreeNode cur = queue.poll();
            
            for (TreeNode next : cur.children) {
                if (!map.containsKey(next)) {
                    map.put(next, new HashSet<>())
                }
                map.get(next).add(cur);
                if (next == target) {
                    // break; // stop current children
                }
                if (!map.containsKey(next)) {
                    queue.offer(next);
                }
                
            }
        }

        return getPath(map);
    }

    List<List<TreeNode>> getPath(TreeNode start, TreeNode end, Map<TreeNode, Set<TreeNode>> map) {
        List<List<TreeNode>> res = new ArrayList<List<TreeNode>>();
        List<TreeNode> list = new ArrayList<>();

        TreeNode cur = end;
        list.add(cur);
        while (cur != null && cur != start) {
        	// this is not working for multiple paths
            Set<TreeNode> set = map.get(cur);
            for (Node next : set) {
                list.add(next);
                cur = next;
            }
        }
    }