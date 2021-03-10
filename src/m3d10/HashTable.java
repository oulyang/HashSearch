package m3d10;

import java.util.Scanner;

public class HashTable {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		HashTableArray hta=new HashTableArray(6);
		
		String key="";
		Scanner scanner=new Scanner(System.in);
		while(true) {
			System.out.println("add:添加人员");
			System.out.println("list:显示人员");
			System.out.println("find:查找人员");
			System.out.println("exit:退出");
			
			key=scanner.next();
			switch(key) {
			case "add":
				System.out.println("请输入编号");
				int no=scanner.nextInt();
				System.out.println("请输入名字");
				String name=scanner.next();
				
				Person person=new Person(no,name);
				hta.add(person);
				break;
			case "list":
				hta.list();
				break;
			case "find":
				System.out.println("请输入编号");
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
	
	//根据no加入对应的链表
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
			System.out.println("在第"+(personLinkedListNo+1)+"条链表中找到编号为"+no+"的人员");
		}else {
			System.out.println("未找到");
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
	//往链表末尾添加
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
			System.out.println("第"+(no+1)+"条链表为空");
			return;
		}
		Person cur=head;
		while(true) {
			System.out.println("id:"+cur.no+"\t姓名："+cur.name);
			if(cur.next==null) {
				break;
			}
			cur=cur.next;
		}
	}
	
	//根据no查找
	public Person find(int no) {
		if(head==null) {
			System.out.println("链表为空");
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