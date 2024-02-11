package basic;

import java.util.Objects;

public class SinglyLinkedList<E> {
    private Node<E> head; //노드의 첫 부분을 가리키는 포인트
    private Node<E> tail; //노드의 마지막 부분을 가리키는 포인트

    private int size;

    public SinglyLinkedList() {
        this.head = null;
        this.tail = null;
        this.size = 0;
    }

    private static class Node<E> {
        private E item; // Node에 담을 데이터
        private Node<E> next; //다음 Node 객체를 가리키는 래퍼런스

        Node(E item, Node<E> next) {
            this.item = item;
            this.next = next;
        }
    }

    public Node<E> search(int index) {
        //head(처음 위치)서부터 차례로 index까지 검색
        Node<E> n = head;
        for(int i = 0; i < index; i++) {
            n = n.next; //next 필드의 값(다음 노드 주소)를 재대입하면서 순차적으로 요소 탐색
        }

        return n;
    }

    public void addFirst(E value) {
        //1. 먼저 가장 앞의 요소를 가져옴
        Node<E> first = head;
        //2. 새 노드 생성(이때 데이터와 next 포인트를 준다.)
        Node<E> newNode = new Node<>(value, first);
        //3. 요소가 추가되었으니 size를 늘린다.
        size++;
        //4. 맨앞에 요소가 추가되었으니 head를 업데이트한다.
        head = newNode;
        //5. 만일 최초로 요소가 add된 것이면 head와 tail이 가리키는 요소가 같게 된다.
        if(first == null) {
            tail = newNode;
        }
    }

    public void addLast(E value) {
        //1. 먼저 가장 뒤의 요소를 가져옴
        Node<E> last = tail;
        //2. 새 노드 생성(맨 마지막 요소 추가니까 next는 null이다)
        Node<E> newNode = new Node<>(value, null);
        //3. 요소가 추가되었으니 size를 늘린다.
        size++;
        //4. 맨뒤에 요소가 추가되었으니 tail을 업데이트한다.
        tail = newNode;

        if(last == null) {
            //5. 만일 최초로 요소가 add된 것이면 head와 tail이 가리키는 요소가 같게 된다.
            head = newNode;
        } else {
            //6. 최초 추가가 아니라면 last 변수(추가 되기 전 마지막이었던 요소)에서 추가된 새 노드를 가리키도록 업데이트
            last.next = newNode;
        }
    }

    public boolean add(E value) {
        addLast(value);
        return true;
    }

    public void add(int index, E value) {
        //1. 인덱스가 0보다 작거나 size 보다 같거나 클 경우 에러
        if(index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }

        //2. 추가하려는 index가 0이면 addFirst 호출
        if(index == 0) {
            addFirst(value);
            return;
        }

        //3. 추가하려는 index가 size - 1이면 addLast 호출
        if(index == size - 1) {
            addLast(value);
            return;
        }

        //4. 추가하려는 위치의 이전 노드 얻기
        Node<E> prev_node = search(index - 1);

        //5. 추가하려는 위치의 다음 노드 얻기
        Node<E> next_node = prev_node.next;

        //6. 새 노드 생성(바로 다음 노드와 연결_
        Node<E> newNode = new Node<>(value, next_node);

        //7. size 증가
        size++;

        //8. 이전 노드를 새 노드와 연결
        prev_node.next = newNode;
    }

    public E removeFirst() {

        //1. 만약 삭제할 요소가 아무것도 없으면 에러
        if(head == null) {
            throw new IndexOutOfBoundsException();
        }

        //2. 삭제될 첫번째 요소의 데이터를 백업
        E returnValue = head.item;

        //3. 두번째 노드를 임시 저장
        Node<E> first = head.next;

        //4. 첫번째 노드의 내부 요소를 모두 삭제
        head.next = null;
        head.item = null;

        //5. head가 다음 노드를 가리키도록 업데이트
        head = first;

        //6. 요소가 삭제 되었으니 크기 감소
        size--;

        //7. 만일 리스트의 유일한 값을 삭제해서 빈 리스트가 될 경우, tail도 null 처리
        if(head == null) {
            tail = null;
        }

        //8. 마지막으로 삭제된 요소를 반환
        return returnValue;
    }

    public E remove() {
        return removeFirst();
    }

    public E remove(int index) {
        if(index < 0 || index >= size) {
           throw new IndexOutOfBoundsException();
        }

        if(index == 0) {
            return removeFirst();
        }

        Node<E> prev_node = search(index - 1);
        Node<E> del_node = prev_node.next;
        Node<E> next_node = del_node.next;

        E returnValue = del_node.item;
        del_node.next = null;
        del_node.item = null;

        size--;

        prev_node.next = next_node;

        return returnValue;
    }

    public boolean remove(E value) {
        //1. 만약 삭제할 요소가 아무것도 없으면 에러
        if(head == null) {
            throw new RuntimeException();
        }

        //2. 이전 노드, 삭제 노드, 다음 노드를 저장할 변수 선언
        Node<E> prev_node = null;
        Node<E> del_node = null;
        Node<E> next_node = null;

        //3. 루프 변수 선언
        Node<E> i = head;

        //4. 노드의 next를 순회하면서 해당 값을 찾는다.
        while(i != null) {
            if(Objects.equals(i.item, value)) {
                del_node = i;
                break;
            }
            prev_node = i;
            i = i.next;
        }

        if(del_node == null) {
            return false;
        }

        if(del_node == head) {
            removeFirst();
            return true;
        }

        next_node = del_node.next;

        del_node.next = null;
        del_node.item = null;

        size--;

        prev_node.next = next_node;

        return true;
    }

    public E removeLast() {
        return remove(size - 1);
    }

    public E get(int index) {
        if(index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }

        return search(index).item;
    }

    public void set(int index, E value) {
        if(index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }

        Node<E> replace_node = search(index);

        replace_node.item = null;
        replace_node.item = value;
    }
//    public static void main(String[] args) {
//        SinglyLinkedList<Number> l = new SinglyLinkedList<>();
//
//        l.add(3);
//        l.add(6);
//        l.add(4);
//        l.add(3);
//        l.add(8);
//        l.add(10);
//        l.add(11);
//        for(int i = 0; i < l.size; i++) {
//            System.out.print(l.get(i) + " ");
//        }
//        System.out.println(); // [3, 6, 4, 3, 8, 10, 11]
//
//        l.add(6, 100);
//        l.add(0, 101);
//        l.add(1, 102);
//        for(int i = 0; i < l.size; i++) {
//            System.out.print(l.get(i) + " ");
//        }
//        System.out.println(); // [101, 102, 3, 6, 4, 3, 8, 10, 11, 100]
//
//        l.removeFirst();
//        l.removeFirst();
//        l.remove(1);
//        for(int i = 0; i < l.size; i++) {
//            System.out.print(l.get(i) + " ");
//        }
//        System.out.println(); // [3, 4, 3, 8, 10, 11, 100]
//
//        l.remove(new Integer(3));
//        l.remove(new Integer(3));
//        for(int i = 0; i < l.size; i++) {
//            System.out.print(l.get(i) + " ");
//        }
//        System.out.println(); // [4, 8, 10, 11, 100]
//
//        System.out.println(l.get(4)); // 100
//
//        l.set(4, 999);
//        for(int i = 0; i < l.size; i++) {
//            System.out.print(l.get(i) + " ");
//        }
//        System.out.println(); // [4, 8, 10, 11, 999]
//    }
}
