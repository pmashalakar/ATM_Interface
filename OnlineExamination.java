import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.lang.Exception;
import java.util.Timer;
import java.util.TimerTask;

class loginfunction extends JFrame implements ActionListener {
    JButton button;
    JPanel newPanel;
    JLabel userLabel, passLabel;
    JTextField usertextfield, textField2;

    loginfunction() {
        userLabel = new JLabel();
        userLabel.setText("Enter username here");

        usertextfield = new JTextField(15);

        passLabel = new JLabel();
        passLabel.setText("Enter Password");

        textField2 = new JPasswordField(15);

        button = new JButton("SUBMIT");
        newPanel = new JPanel(new GridLayout(3, 1));
        newPanel.add(userLabel);
        newPanel.add(usertextfield);
        newPanel.add(passLabel);
        newPanel.add(textField2);
        newPanel.add(button);
        add(newPanel);
        button.addActionListener(this);
        setTitle("***************  Login for the Examination  *********************");

    }

    public void actionPerformed(ActionEvent ae) {
        String userValue = usertextfield.getText();
        String passValue = textField2.getText();

        if (!passValue.equals(""))
            new OnlineExam(userValue);
        else {
            textField2.setText("Enter Password");
            actionPerformed(ae);
        }
    }

}

class OnlineExam extends JFrame implements ActionListener {
    JLabel que;
    JLabel timerr;

    JRadioButton radioButton[] = new JRadioButton[6];
    JButton savebutt, revbutt;
    ButtonGroup bg;
    int count = 0, current = 0, x = 1, y = 1, now = 0;
    int m[] = new int[10];
    Timer timer = new Timer();

    OnlineExam(String s) {

        super(s);

        que = new JLabel();
        timerr = new JLabel();

        add(que);
        add(timerr);
        bg = new ButtonGroup();
        for (int i = 0; i < 5; i++) {
            radioButton[i] = new JRadioButton();
            add(radioButton[i]);
            bg.add(radioButton[i]);
        }
        savebutt = new JButton("Save and Next");
        revbutt = new JButton("For Mark for Review");
        savebutt.addActionListener(this);
        revbutt.addActionListener(this);
        add(savebutt);
        add(revbutt);
        set();
        que.setBounds(30, 40, 450, 20);
        timerr.setBounds(600, 20, 200, 10);
        radioButton[0].setBounds(50, 80, 100, 20);
        radioButton[1].setBounds(50, 110, 100, 20);
        radioButton[2].setBounds(50, 140, 100, 20);
        radioButton[3].setBounds(50, 170, 100, 20);
        setTitle("*****************************  Online Examination  ***************************");
        savebutt.setBounds(95, 240, 140, 30);
        revbutt.setBounds(270, 240, 150, 30);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        setLocation(250, 200);
        setVisible(true);
        setSize(800, 850);
        timer.scheduleAtFixedRate(new TimerTask() {
            int i = 120;

            public void run() {

                timerr.setText("Timer: " + i);
                i--;

                if (i < 0) {
                    timer.cancel();
                    timerr.setText("              Time Over");
                }
            }
        }, 0, 1000);

    }

    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == savebutt) {
            if (check())
                count = count + 1;
            current++;
            set();
            if (current == 9) {
                savebutt.setEnabled(false);
                revbutt.setText("Result");
            }
        }
        if (e.getActionCommand().equals("Marked for Review")) {
            JButton bk = new JButton("Review" + x);
            bk.setBounds(480, 20 + 30 * x, 100, 30);
            add(bk);
            bk.addActionListener(this);
            m[x] = current;
            x++;
            current++;
            set();
            if (current == 9)
                revbutt.setText("Result");
            setVisible(false);
            setVisible(true);
        }
        for (int i = 0, y = 1; i < x; i++, y++) {
            if (e.getActionCommand().equals("Review" + y)) {
                if (check())
                    count = count + 1;
                now = current;
                current = m[y];
                set();
                ((JButton) e.getSource()).setEnabled(false);
                current = now;
            }
        }

        if (e.getActionCommand().equals("Result")) {
            if (check())
                count = count + 1;
            current++;

            JOptionPane.showMessageDialog(this, "Your Score =" + count);
            if (count > 5)
                JOptionPane.showMessageDialog(this, "Good Score congrats " + count);
            if (count < 5)
                JOptionPane.showMessageDialog(this, "you Have to improve ");
            System.exit(0);
        }
    }

    void set() {
        radioButton[4].setSelected(true);
        if (current == 0) {
            que.setText("Que1:  Which one of the following is a java keyword?");
            radioButton[0].setText("Switch");
            radioButton[1].setText("While");
            radioButton[2].setText("Public");
            radioButton[3].setText("Void");
        }
        if (current == 1) {
            que.setText("Que2:  Java declaration statement must end with");
            radioButton[0].setText("Comma");
            radioButton[1].setText("A colon");
            radioButton[2].setText("A semicolon");
            radioButton[3].setText("Full stop");
        }
        if (current == 2) {
            que.setText("Que3: Which one of the following languages is suitable to implement the OOP concepts?");
            radioButton[0].setText("Objective C and C++");
            radioButton[1].setText("Small talk");
            radioButton[2].setText("Ada");
            radioButton[3].setText("All of these");
        }
        if (current == 3) {
            que.setText("Que4: 	Java is a general purpose object oriented programming language developed by");
            radioButton[0].setText("Microsystems of U.S.A.");
            radioButton[1].setText("I.B.M.");
            radioButton[2].setText("Patrick Naughton");
            radioButton[3].setText("None of these");
        }
        if (current == 4) {
            que.setText("Que5: In an object oriented programming");
            radioButton[0].setText("Class create objects");
            radioButton[1].setText("Objects create classes");
            radioButton[2].setText("Classes use methods to communicate between them");
            radioButton[3].setText("None of these");
        }
        if (current == 5) {
            que.setText("Que6: How to read entire file in one line using java 8?");
            radioButton[0].setText("Files.readAllLines()");
            radioButton[1].setText("Files.read()");
            radioButton[2].setText("Files.readFile()");
            radioButton[3].setText("Files.lines()");
        }
        if (current == 6) {
            que.setText("Que7: Which one of the following concept is incorporated in building a java program?");
            radioButton[0].setText("Encapsulation");
            radioButton[1].setText("Inheritance");
            radioButton[2].setText("Polymorphism");
            radioButton[3].setText("All of these");
        }
        if (current == 7) {
            que.setText(
                    "Que8: The bitwise AND operator is represented by a single ampersand (&) and is surrounded on both sides by");
            radioButton[0].setText("Integer expression");
            radioButton[1].setText("Colons");
            radioButton[2].setText("Semicolons");
            radioButton[3].setText("Commas");
        }
        if (current == 8) {
            que.setText("Que9: A package is a collection of");
            radioButton[0].setText("Interfaces");
            radioButton[1].setText("Classes");
            radioButton[2].setText("Both (a) and (b)");
            radioButton[3].setText("None of these");
        }
        if (current == 9) {
            que.setText("Que10: Java does not define which type modifier?");
            radioButton[0].setText("Auto");
            radioButton[1].setText("Extern");
            radioButton[2].setText("Register");
            radioButton[3].setText("All of these");
        }
        que.setBounds(30, 40, 450, 20);
        for (int i = 0, j = 0; i <= 90; i += 30, j++)
            radioButton[j].setBounds(50, 80 + i, 200, 20);
    }

    boolean check() {
        if (current == 0)
            return (radioButton[2].isSelected());
        if (current == 1)
            return (radioButton[2].isSelected());
        if (current == 2)
            return (radioButton[3].isSelected());
        if (current == 3)
            return (radioButton[0].isSelected());
        if (current == 4)
            return (radioButton[0].isSelected());
        if (current == 5)
            return (radioButton[0].isSelected());
        if (current == 6)
            return (radioButton[3].isSelected());
        if (current == 7)
            return (radioButton[2].isSelected());
        if (current == 8)
            return (radioButton[3].isSelected());
        if (current == 9)
            return (radioButton[3].isSelected());
        return false;
    }

}

public class OnlineExamination {

    public static void main(String arg[]) {

        loginfunction login = new loginfunction();

        login.setSize(500, 200);
        login.setLocation(250, 250);
        login.setVisible(true);

    }
}
