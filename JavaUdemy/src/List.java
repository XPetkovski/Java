public class List extends Employee {

    public static void main (String[] args)
    {
        Employee JaneJones = new Employee("Jane", "Jones", 123);
        Employee JohnDoe = new Employee("John", "Doe", 4567);
        Employee MarySmith = new Employee("Mary", "Smith", 22);
        Employee MikeWilson = new Employee("Mike", "Wilson", 3245);


        EmployeeLinkedList List = new EmployeeLinkedList();
        List.addtofront(JaneJones);
        List.addtofront(JohnDoe);
        List.addtofront(MarySmith);
        List.addtofront(MikeWilson);
        //System.out.println(List.isEmpty());
        //System.out.println(List.getSize());
        //System.out.println(List.removeFromFront());



        Employee billEnd = new Employee("Bill", "End", 78);
        List.addtoend(billEnd);
        List.printList();
        System.out.println(List.getSize());
        List.removeFromFront();
        List.printList();
        System.out.println(List.getSize());
        List.removeFromEnd();
        List.printList();
        System.out.println(List.getSize());
    }

}

class EmployeeNode{

    private Employee employee;
    private EmployeeNode next;
    private EmployeeNode previous;

    public EmployeeNode(Employee employee)
    {
        this.employee = employee;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public EmployeeNode getNext() {
        return next;
    }

    public void setNext(EmployeeNode next) {
        this.next = next;
    }

    public EmployeeNode getPrevious() {
        return previous;
    }

    public void setPrevious(EmployeeNode previous) {
        this.previous = previous;
    }

    public String toString()
    {
        return employee.toString();
    }

}

class EmployeeLinkedList
{
    private EmployeeNode head;
    private EmployeeNode tail;
    private int size;

    public int getSize()
    {
        return size;
    }

    public boolean isEmpty()
    {
        return head == null;
    }
    public void addtofront(Employee employee)
    {
        EmployeeNode node = new EmployeeNode(employee);
        node.setNext(head);
        if(head==null)
        {
            tail = node;
        }
        else head.setPrevious(node);
        node.setNext(head);

        head = node;
        size++;
    }
    public void addtoend(Employee employee)
    {
        EmployeeNode node = new EmployeeNode(employee);
        if(tail == null)
        {
            head = node;
        }
        else {
            tail.setNext(node);
            node.setPrevious(tail);
        }
        tail = node;
        size++;
    }

    public EmployeeNode removeFromFront()
    {
        if(isEmpty())
        {
            return null;
        }

        EmployeeNode removedNode = head;
        if(head.getNext()==null)
        {
            tail = null;
        }
        else {
            head.getNext().setPrevious(null);
        }
        head = head.getNext();
        size--;
        removedNode.setNext(null);
        return removedNode;
    }

    public EmployeeNode removeFromEnd()
    {
        if(isEmpty())
        {
            return null;
        }
        EmployeeNode removedNode = tail.getPrevious();

        if(tail.getPrevious()==null)
        {
            head = null;
        }
        else {
            tail.getPrevious().setNext(null);
        }
        tail = tail.getPrevious();
        size--;
        removedNode.setPrevious(null);
        return removedNode;
    }

    public void printList() {
        EmployeeNode current = head;
        System.out.println("HEAD-> ");
        while(current!=null)
        {
            System.out.println(current);
            System.out.println(" <=> ");
            current = current.getNext();
        }
        System.out.println("null");
    }
}