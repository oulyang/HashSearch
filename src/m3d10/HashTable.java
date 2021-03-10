package m3d10;

import java.util.Scanner;

public class HashTable {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		HashTableArray hta=new HashTableArray(6);
		
		String key="";
		Scanner scanner=new Scanner(System.in);
		while(true) {
			System.out.println("add:�����Ա");
			System.out.println("list:��ʾ��Ա");
			System.out.println("find:������Ա");
			System.out.println("exit:�˳�");
			
			key=scanner.next();
			switch(key) {
			case "add":
				System.out.println("��������");
				int no=scanner.nextInt();
				System.out.println("����������");
				String name=scanner.next();
				
				Person person=new Person(no,name);
				hta.add(person);
				break;
			case "list":
				hta.list();
				break;
			case "find":
				System.out.println("��������");
				int id=scanner.nextInt();
				hta.find(id);
				break;
			case "exit":
				scanner.close();
				System.exit(0);
			default:
				break;
			}
		}
	}

}

class HashTableArray{
	private int size;
	private PersonLinkedList[] personLinkedListArray;
	
	public HashTableArray() {
	}
	public HashTableArray(int size) {
		this.size=size;
		personLinkedListArray=new PersonLinkedList[size];
		for(int i=0;i<size;i++) {
			personLinkedListArray[i]=new PersonLinkedList();
		}
	}
	
	//����no�����Ӧ������
	public void add(Person person) {
		int personLinkedListNo=HashFun(person.no);
		personLinkedListArray[personLinkedListNo].add(person);
	}
	
	public int HashFun(int no) {
		return no%size;
	}
	
	public void list() {
		for(int i=0;i<size;i++) {
			personLinkedListArray[i].list(i);
		}
	}
	public void find(int no) {
		int personLinkedListNo=HashFun(no);
		Person person=personLinkedListArray[personLinkedListNo].find(no);
		if(person!=null){
			System.out.println("�ڵ�"+(personLinkedListNo+1)+"���������ҵ����Ϊ"+no+"����Ա");
		}else {
			System.out.println("δ�ҵ�");
		}
	}
}
class Person{
	public int no;
	public String name;
	public Person next;
	public Person() {
		
	}
	public Person(int no,String name) {
		this.no=no;
		this.name=name;
	}
}

class PersonLinkedList{
	private Person head;
	//������ĩβ���
	public void add(Person person) {
		if(head==null) {
			head=person;
			return;
		}
		Person cur=head;
		while(true) {
			if(cur.next==null) {
				break;
			}
			cur=cur.next;
		}
		cur.next=person;
	}
	
	public void list(int no) {
		if(head==null) {
			System.out.println("��"+(no+1)+"������Ϊ��");
			return;
		}
		Person cur=head;
		while(true) {
			System.out.println("id:"+cur.no+"\t������"+cur.name);
			if(cur.next==null) {
				break;
			}
			cur=cur.next;
		}
	}
	
	//����no����
	public Person find(int no) {
		if(head==null) {
			System.out.println("����Ϊ��");
			return null;
		}
		Person cur=head;
		while(true) {
			if(cur.no==no) {
				break;
			}
			if(cur.next==null) {
				cur=null;
				break;
			}
			cur=cur.next;
		}
		return cur;
	}
}