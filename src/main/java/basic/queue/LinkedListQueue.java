package basic.queue;
class Node<E> {
    E data;
    Node<E> next; //다음 노드를 가리키는 역할

    Node(E data) {
        this.data = data;
        this.next = null;
    }
}

public class LinkedListQueue<E> {
    private Node<E> head;
    private Node<E> tail;
    private int size;

    public LinkedListQueue() {
        this.head = null;
        this.tail = null;
        this.size = 0;
    }

    public boolean offer(E value) {
        Node<E> newNode = new Node<E>(value);

        if(size == 0) { //비어있으면, head에 삽입
            head = newNode;
        } else { // 그 외의 경우 마지막 노드(tail)의 다음 노드(next)가 새 노드를 가리키도록 한다.
            tail.next =  newNode;
        }

        // tail을 새로 입력한 노드로 변경
        tail = newNode;
        size++;

        return true;
    }
    public E poll() {
        if(size == 0) { //비어있으면, 삭제할 노드 없으므로 null 반환
            return null;
        }

        //삭제할 요소의 데이터 반환위한 임시변수(temp)
        E element = head.data;
        Node<E> nextNode = head.next;

        //head 모든 데이터 삭제
        head.data = null;
        head.next = null;

        //head가 가리키는 노드를 삭제된 head노드의 다음노드 가리키도록 변경
        head = nextNode;
        size--;

        return element;
    }
}
