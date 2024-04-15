class Node:
    """ Create a node for tree """
    def __init__(self,data):
        self.data = data
        self.left = None
        self.right = None

class BinaryTree:

    def __init__(self):
        self.root = None
        self.height = 0
        self.count = 0
    
    def insert(self, data:int, root:Node) -> Node:
        if root == None:
            root = Node(data)
            return root
        if data < root.data:
            root.left = self.insert(data,root.left)
        else:
            root.right = self.insert(data,root.right)
        return root
    
    def display(self,root:Node):
        if root != None:
            self.display(root.left)
            print(root.key)
            self.display(root.right)


