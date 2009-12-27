package shane.vo;


public class User
{
   
	private int uid;
	private String account;
	private String password;
	private String name;
	private boolean gender;
	private int age;
	private String email;
	private String question;
	private String answer;
	private String image;
	private int identify;
	private boolean isLocked;
   
	
	public void setUid(int uid) {
		this.uid = uid;
	}
	public int getUid() {
		return uid;
	}
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public boolean getGender() {
		return gender;
	}
	public void setGender(boolean gender) {
		this.gender = gender;
	}
	
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	public String getEmail() {
		return email;
	}
	
	public String getQuestion() {
		return question;
	}
	public void setQuestion(String question) {
		this.question = question;
	}
	
	public String getAnswer() {
		return answer;
	}
	public void setAnswer(String answer) {
		this.answer = answer;
	}
	
	public void setImage(String image) {
		this.image = image;
	}
	public String getImage() {
		return image;
	}
	
	public int getIdentify() {
		return identify;
	}
	public void setIdentify(int identify) {
		this.identify = identify;
	}
	
	public void setLocked(boolean isLocked) {
		this.isLocked = isLocked;
	}
	public boolean isLocked() {
		return isLocked;
	}

	   
}
