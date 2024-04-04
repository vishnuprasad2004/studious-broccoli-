class Node:
    """ Create a node for a Single linked list """
    def __init__(self, data:int):
        self.data = data
        self.next = None

class LinkedList:
    
    def __init__(self) -> None:
        self.head = None

    def insert_first(self, data:int):
        newNode = Node(data)
        if self.head == None:
            self.head = newNode
        else:
            newNode.next = self.head
            self.head = newNode
        
    def insert_last(self, data:int):
        newNode = Node(data)
        if self.head == None:
            self.head = newNode
        else:
            temp = self.head
            while temp.next != None:
                temp = temp.next
            temp.next = newNode
    
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

    def remove_last(self):
        if self.head.next == None:
            # only one node is present
            self.head = None
        else:
            second_last = self.head
            while(second_last.next.next):
                second_last = second_last.next
            second_last.next = None


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
ll.insert_last(0)
ll.print_list()
ll.remove_first()
ll.remove_last()
ll.print_list()
