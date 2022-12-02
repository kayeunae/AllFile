package School.report;

import java.util.ArrayList;

import Grade.BasicEvaluation;
import Grade.GradeEvaluation;
import Grade.MajorEvaluation;
import School.*;
import Utils.Define;

public class GenerateGradeReport {
	// 필드
	School school = School.getInstance(); // school 싱글톤 객체 생성

	public static final String TITLE = "수강생 학점 \t\t\t\n";
	public static final String HEADER = "이름 | 학번 | 필수과목 | 점수		\n";
	public static final String LINE = "-----------------------------------\n";
	private StringBuffer buffer = new StringBuffer(); // String buffer = ""; 와 같은 상태

	// 성적 산출 결과의 헤더, 몸통, 푸터를 합쳐준다.
	public String getReport() {
		ArrayList<Subject> subjectList = school.getSubjectList();	//과목 리스트 불러오기
		for(Subject subject : subjectList) {
			makeHeader(subject);
			makeBody(subject);
			makeFooter();
		}
		
		return buffer.toString();	//buffer의 데이터를 String 타입으로 바꾼다.
	}

	// 헤더를 만들어준다.
	public void makeHeader(Subject subject) {
		buffer.append(GenerateGradeReport.LINE);
		buffer.append("\t" + subject.getSubjectName());
		buffer.append(GenerateGradeReport.TITLE);
		buffer.append(GenerateGradeReport.HEADER);
		buffer.append(GenerateGradeReport.LINE);
	}

	
	
	
	// 몸통을 만들어준다.
	public void makeBody(Subject subject) {
		//해당 과목을 수강신청한 학생 리스트를 가져온다
		ArrayList<Student> studentList = subject.getStudentList();
		
		for(int i = 0; i < studentList.size(); i++) {
			Student student = studentList.get(i);
			buffer.append(student.getStudentName());	//이름
			buffer.append(" | ");
			buffer.append(student.getStudentId());		//학번
			buffer.append(" | ");
			buffer.append(student.getMajorSubject().getSubjectName() + "\t"); //학생 필수과목
			buffer.append(" | ");
			
			//학생별 수강과목의 점수 학점 계산
			getScoreGrade(student, subject.getSubjectId());
			buffer.append("\n");
			buffer.append(LINE);
		}
	}
	
	//학생의 수강과목 점수와 학점을 얻는 메소드.
	public void getScoreGrade(Student student, int subjectId) {
		//해당 학생이 수강한 과목의 점수 리스트(국어, 수학)
		ArrayList<Score> scoreList = student.getScoreList();
		
		//해당 학생의 필수 과목 코드
		int majorId = student.getMajorSubject().getSubjectId();
		
//		GradeEvaluation graeEvaluation1 = new MajorEvaluation();
//		GradeEvaluation graeEvaluation2 = new BasicEvaluation();
		
		//바로 윗 두 줄과 같은 코드. 객체를 배열에 넣어준다.
		GradeEvaluation[] gradeEvaluation = {new BasicEvaluation(), new MajorEvaluation()};
		
		for (int i = 0; i < scoreList.size(); i++) {
			Score score = scoreList.get(i);	//해당 과목의 점수를 받아온다.
			
			//과목 코드가 같은지 확인
			if(score.getSubject().getSubjectId() == subjectId) {
				String grade; //학점
				
				//필수 과목일 때 학점 산출
				if(score.getSubject().getSubjectId() == majorId) {
					grade = gradeEvaluation[Define.SAB_TYPE].getGrade(score.getPoint());
				} else { //일반 과목일 때 학점 산출
					grade = gradeEvaluation[Define.AB_TYPE].getGrade(score.getPoint());
				}
				
				buffer.append(score.getPoint());	
				
				buffer.append(":");
				buffer.append(grade);
				buffer.append(" | ");
			}
		}
	}

	// 푸터를 만들어준다.
	public void makeFooter() {
		buffer.append("\n"); //buffer += "\n" 과 같은 의미
	}

}
