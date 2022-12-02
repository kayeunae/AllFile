package Application;

import School.*;
import School.report.GenerateGradeReport;
import Utils.Define;

public class StudentInfoApplication {
	School goodSchool = School.getInstance();
	Subject korean;	//국어 과목 생성
	Subject math;	//수학 과목 생성
	GenerateGradeReport gradeReport	= new GenerateGradeReport();
	
	public static void main(String[] args) {
		StudentInfoApplication test = new StudentInfoApplication();
		
		test.createSubject();	//createSubject 메소드 실행
		test.createStudent();	//createStudent 메소드 실행
		
		String report = test.gradeReport.getReport(); //성적 결과 생성
		System.out.println(report);
		
	}
	
	//과목을 생성
	public void createSubject() {
		korean = new Subject("국어", Define.KOREA);	// korean Subject 객체 생성
		math = new Subject("수학", Define.MATH);	
		// math Subject 객체 생성
		
		
		//학교에 과목을 넣어준다.
		goodSchool.addSubject(korean);		//School 클래스의 addSubject 메소드에 매개변수로 korean 객체를 넣어줌 => (School)subjectList 객체 배열에 과목을 korean 과목 객체 삽입
		goodSchool.addSubject(math);		//School 클래스의 addSubject 메소드에 매개변수로 math 객체를 넣어줌 => (School)subjectList 객체 배열에 과목을 math 과목 객체 삽입
	}
	
	//학생 생성
	public void createStudent() {
		Student student1 = new Student(181213, "박지은", korean);		//student 객체 생성
		Student student2 = new Student(181518, "차슬기", math);
		Student student3 = new Student(171230, "이현욱", korean);
		Student student4 = new Student(171255, "임지연", korean);
		Student student5 = new Student(171590, "김재현", math);
		
		
		//학교에 등록
		goodSchool.addStudent(student1);		//생성한 student 객체를 addstudent 메소드를 통해 (School)studentList 객체 배열에 삽입
		goodSchool.addStudent(student2);
		goodSchool.addStudent(student3);
		goodSchool.addStudent(student4);
		goodSchool.addStudent(student5);
		
		//수강신청
		korean.register(student1);			//과목에 수강신청한 학생 객체를 register 메소드를 통해 (Subject-korean)studentList 배열에 넣어줌.
		korean.register(student2);
		korean.register(student3);
		korean.register(student4);
		korean.register(student5);
		
		math.register(student1);			//과목에 수강신청한 학생 객체를 register 메소드를 통해 (Subject-math)studentList 배열에 넣어줌.
		math.register(student2);
		math.register(student3);
		math.register(student4);
		math.register(student5);
		
		//학생의 과목별 점수 추가
		addScoreForStudent(student1, korean, 95);
		addScoreForStudent(student1, math, 56);
		
		addScoreForStudent(student2, korean, 95);
		addScoreForStudent(student2, math, 95);
		
		addScoreForStudent(student3, korean, 100);
		addScoreForStudent(student3, math, 88);
		
		addScoreForStudent(student4, korean, 89);
		addScoreForStudent(student4, math, 95);
		
		addScoreForStudent(student5, korean, 85);
		addScoreForStudent(student5, math, 55);
		
	}
	
	//학생마다 과목별(국어, 수학) 점수 추가
	public void addScoreForStudent(Student student, Subject subject, int point) {
		//점수 객체 생성
		Score score = new Score(student.getStudentId(), subject, point);	// student객체의 학번을 get 메소드로 가져옴(private라서), subject 객체, 과목의 점수
		//각 학생에게 점수를 넣어주는 메소드
		student.addSubjectScore(score);
		
	}
	
}
