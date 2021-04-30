// Kim-Long Do
// 3/2/2021
// CSE143
// TA: Chandan Hegde
// Take-Home Assessment #7
//
// QuestionNode class is used to store the data for one node in a tree

public class QuestionNode {
    public String data; 
    public QuestionNode yes;
    public QuestionNode no;

    // Constructs a leaf with given data
    public QuestionNode(String data) {
        this(data, null, null);
    }

    // Constructs a node with given data and links
    public QuestionNode(String data, QuestionNode yes, QuestionNode no) {
        this.data = data;
        this.yes = yes;
        this.no = no;
    }
}
