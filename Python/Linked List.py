class Node:
    """ Create a node for a Single linked list """
    def __init__(self, data:int):
        self.data = data
        self.next = None

class LinkedList:
    
    def __init__(self) -> None:
        self.head = None
        self.count = 0

    def insert_first(self, data:int):
        newNode = Node(data)
        if self.head == None:
            self.head = newNode
        else:
            newNode.next = self.head
            self.head = newNode
        self.count += 1
        
    def insert_last(self, data:int):
        newNode = Node(data)
        if self.head == None:
            self.head = newNode
        else:
            temp = self.head
            while temp.next != None:
                temp = temp.next
            temp.next = newNode
        self.count += 1
    
    def print_list(self):
        if self.head == None:
            print("Linked List is Empty")
            return
        temp = self.head
        while temp != None:
            print(temp.data, "-> ", end="")
            temp = temp.next
        print("None")

    def remove_first(self):
        if self.head.next == None:
            # only one node is present
            self.head = None
        else:
            self.head = self.head.next
        self.count -= 1

    def remove_last(self):
        if self.head.next == None:
            # only one node is present
            self.head = None
        else:
            second_last = self.head
            while(second_last.next.next):
                second_last = second_last.next
            second_last.next = None
        self.count -= 1

    def insert_middle(self,data:int,n:int):
        if n == 0:
            self.insert_first(data)
            return
        newNode = Node(data)
        temp, i = self.head, 0
        while i < n-1 and temp != None:
            temp = temp.next
            i += 1
        newNode.next = temp.next
        temp.next = newNode


    def remove_middle(self,n:int):
        if n == self.count:
            self.remove_last()
            return
        temp, i = self.head, 0
        while i < n-1 and temp != None:
            temp = temp.next
            i += 1
        temp.next = temp.next.next


    def search(self,item:int) -> int:
        temp = self.head
        idx = 0
        while temp != None:
            if temp.data == item:
                return idx
            temp = temp.next
            idx += 1
        return -1

    def find_middle(self):
        slow = self.head
        fast = self.head
        while fast != None or fast.next != None:
            slow = slow.next
            fast = fast.next
            fast = fast.next

ll = LinkedList()
ll.insert_first(10)
ll.insert_first(20)
ll.insert_first(30)
ll.insert_middle(25,2)
ll.insert_middle(25,3)
ll.print_list()
ll.remove_middle(3)
ll.insert_last(0)
ll.print_list()
ll.remove_first()
ll.remove_last()
ll.print_list()
