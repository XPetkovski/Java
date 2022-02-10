import java.util.Scanner;


    static class SLLNode {
        String index;
        String name;
        int points;
        SLLNode succ;

        public SLLNode(String index, String name, int points, SLLNode succ) {
            this.index = index;
            this.name = name;
            this.points = points;
            this.succ = succ;
        }

        @Override
        public String toString() {
            return name;
        }
    }

    public static class SLL {
        SLLNode first;

        public SLL() {
            this.first = null;
        }

        public void insertFirst(String index, String name, int points) {
            first = new SLLNode(index, name, points, first);
        }

        public void insertLast(String index, String name, int points) {
            if (first != null) {
                SLLNode tmp = first;
                while (tmp.succ != null)
                    tmp = tmp.succ;
                tmp.succ = new SLLNode(index, name, points, null);
            } else {
                insertFirst(index, name, points);
            }
        }

        public void deleteFirst() {
            if (first != null) {
                first = first.succ;
            } else {
                System.out.println("Listata e prazna");
            }
        }

        public void delete(SLLNode node) {
            if (first != null) {
                SLLNode tmp = first;
                if(first ==node){
                    this.deleteFirst();
                }
                while (tmp.succ != node && tmp.succ.succ != null)
                    tmp = tmp.succ;
                if (tmp.succ == node) {
                    tmp.succ = tmp.succ.succ;
                } else {
                    System.out.println("Elementot ne postoi vo listata");
                }
            } else {
                System.out.println("Listata e prazna");
            }

        }

        @Override
        public String toString() {
            StringBuilder ret = new StringBuilder();
            if (first != null) {
                SLLNode tmp = first;
                ret.append(tmp).append("\n");
                while (tmp.succ != null) {
                    tmp = tmp.succ;
                    ret.append(tmp).append("\n");
                }
            } else
                ret = new StringBuilder("NO ELEMENTS");
            return ret.toString();
        }
    }

    public static class Students {

        public static void removeStudent(SLL students) {
            //todo: implement function
            int min = 101;
            String name = "";
            String index = "";
            SLLNode student = students.first;
            while(student != null){
                if(student.points < min){
                    min = student.points;
                    name = student.name;
                    index = student.index;
                }
                student = student.succ;
            }
            student = students.first;
            while(student != null){
                if(student.points == min && student.name == name && student.index.equals(index)){
                    students.delete(student);
                    break;
                }
                student = student.succ;
            }
        }

        public static void main(String[] args)
        {
            Scanner scanner =  new Scanner(System.in);
            int n = Integer.parseInt(scanner.nextLine());
            SLL students =  new SLL();

            for(int i=0; i<n; i++){
                String line = scanner.nextLine();
                String[] parts = line.split("\\s+");
                students.insertLast(parts[0], parts[1], Integer.parseInt(parts[2]));
            }

            removeStudent(students);
            System.out.println(students.toString());
        }
    }

