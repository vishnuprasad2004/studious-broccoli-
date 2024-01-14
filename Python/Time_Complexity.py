import timeit
# import matplotlib as mat


def linear_search(list, num):
    for i in range(len(list)):
        if list[i] == num:
            return True
    return False


def binary_search(list, num):
    while len(list) > 0:
        mid = (len(list))//2
        if mylist[mid] == num:
            return True
        elif mylist[mid] < num:
            mylist = mylist[:mid]
        else:
            mylist = mylist[mid + 1:]
    return False

def binary_time():
    SETUP_CODE = '''
from __main__ import binary_search
from random import randint'''
 
    TEST_CODE = '''
mylist = [x for x in range(10000)]
find = randint(0, len(mylist))
binary_search(mylist, find)'''
 
    # timeit.repeat statement
    times = timeit.repeat(setup=SETUP_CODE,
                          stmt=TEST_CODE,
                          repeat=3,
                          number=10000)
 
    # printing minimum exec. time
    print('Binary search time: {}'.format(min(times)))
 
 
# compute linear search time
def linear_time():
    SETUP_CODE = '''
from __main__ import linear_search
from random import randint'''
 
    TEST_CODE = '''
mylist = [x for x in range(10000)]
find = randint(0, len(mylist))
linear_search(mylist, find)
    '''
    # timeit.repeat statement
    times = timeit.repeat(setup=SETUP_CODE,
                          stmt=TEST_CODE,
                          repeat=3,
                          number=10000)
 
    # printing minimum exec. time
    print('Linear search time: {}'.format(min(times)))
 


if __name__ == "__main__":
    linear_time()
    binary_time()