import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

class ItemButton extends JButton {
    String name;
    int price;
    int number;

    public ItemButton(String name, int price, int number)
    {
        super();

        this.name = name;
        this.price = price;
        this.number = number;
    }

    public void set_name(String name)
    {
        this.name = name;

        return;
    }

    public void set_price(int price)
    {
        this.price = price;

        return;
    }

    public void set_number(int number)
    {
        this.number = number;

        return;
    }

    public String get_name()
    {
        return this.name;
    }

    public int get_price()
    {
        return this.price;
    }

    public int get_number()
    {
        return this.number;
    }
}

class Item extends JPanel {
    ItemButton item_button;
    JLabel item_label;

    public Item()
    {
        super(new BorderLayout());

        // item_label�̃e�L�X�g�쐬
        String label_text;
        label_text = "";

        // item_label������
        item_label = new JLabel(label_text, JLabel.CENTER);
        this.add(item_label, "Center");

        // item_button������
        item_button = new ItemButton("", 0, 0);
        this.add(item_button, "East");
    }

    public Item(String name, int price)
    {
        // item_label�̃e�L�X�g�쐬
        String label_text;
        if(name == "") {
            label_text = "";
        }
        else {
            label_text = name + ": " + price + "�~";
        }

        // item_label������
        item_label = new JLabel(label_text, JLabel.CENTER);
        this.add(item_label, "Center");

        // item_button������
        item_button = new ItemButton(name, price, 0);
        this.add(item_button, "East");
    }

    public Item(String name, int price, int number)
    {
        super(new BorderLayout());

        // item_label�̃e�L�X�g�쐬
        String label_text;
        if(name == "") {
            label_text = "";
        }
        else if(number <= 0){
            label_text = name + ": " + price + "�~";
        }
        else {
            label_text = name + ": " + price + "�~ �~ " + number;
        }

        // item_label������
        item_label = new JLabel(label_text, JLabel.CENTER);
        this.add(item_label, "Center");

        // item_button������
        item_button = new ItemButton(name, price, number);
        this.add(item_button, "East");
    }

    public void set_name(String name)
    {
        String label_text;

        // �p�����[�^�ύX
        item_button.name = name;
        
        // �e�L�X�g����
        if(item_button.name == "") {
            label_text = "";
        }
        else if(item_button.number <= 0){
            label_text = item_button.name + ": " + item_button.price + "�~";
        }
        else {
            label_text = item_button.name + ": " + item_button.price + "�~ �~ " + item_button.number;
        }

        // �e�L�X�g�Z�b�g
        item_label.setText(label_text);

        return;
    }

    public void set_price(int price)
    {
        String label_text;

        // �p�����[�^�ύX
        item_button.price = price;

        // �e�L�X�g����
        if(item_button.name == "") {
            label_text = "";
        }
        else if(item_button.number <= 0){
            label_text = item_button.name + ": " + item_button.price + "�~";
        }
        else {
            label_text = item_button.name + ": " + item_button.price + "�~ �~ " + item_button.number;
        }

        // �e�L�X�g�Z�b�g
        item_label.setText(label_text);

        return;
    }

    public void set_number(int number)
    {
        String label_text;

        // �p�����[�^�ύX
        item_button.number = number;

        // �e�L�X�g����
        if(item_button.name == "") {
            label_text = "";
        }
        else if(item_button.number <= 0){
            label_text = item_button.name + ": " + item_button.price + "�~";
        }
        else {
            label_text = item_button.name + ": " + item_button.price + "�~ �~ " + item_button.number;
        }

        // �e�L�X�g�Z�b�g
        item_label.setText(label_text);

        return;
    }

    public void set_button_param(String button_text, ActionListener action_listener)
    {
        item_button.setText(button_text);
        item_button.addActionListener(action_listener);

        return;
    }

    public String get_name()
    {
        return item_button.name;
    }

    public int get_price()
    {
        return item_button.price;
    }

    public int get_number()
    {
        return item_button.number;
    }
}

class SelectedItemList extends JPanel {
    Item[] selected_item;

    public SelectedItemList (ActionListener action_listener)
    {
        super(new GridLayout(5,1,5,5));

        selected_item = new Item[5];

        for(int i = 0; i < 5; i++) {
            // selected_item������
            selected_item[i] = new Item();
            this.add(selected_item_panel[i]);

            // selected_item�̃{�^���p�����[�^��ݒ�
            selected_item[i].set_button_param("���ʂ̕ύX", action_listener);
        }
    }

    public int register_item(Item item)
    {
        int res = 0;
        int i = 0;

        for(i = 0; i < 5; i++) {
            if(selected_item[i].get_name() == "") {
                selected_item[i].set_name(item.get_name());
                selected_item[i].set_price(item.get_price());
                selected_item[i].set_number(item.get_number());
                selected_item[i].repaint();
                break;
            }
            else if(selected_item[i].get_name() == item.get_name()) {
                selected_item[i].set_number(item.get_number());
                selected_item[i].repaint();
                break;
            }
        }

        if(i == 5) {
            res = 1;
        }

        return res;
    }

    public int delete_item(Item item) 
    {
        int res = 1;
        int index = 0;
        int i = 0;

        for(i = 0; i < 5; i++) {
            if(selected_item[i].get_name() == item.get_name()) {
                selected_item[i].set_name("");
                selected_item[i].set_price(0);
                selected_item[i].set_number(0);
                selected_item[i].repaint();
                res = 0;
                break;
            }
        }

        for(i = i + 1 ; i < 5; i++) {
            selected_item[i - 1].set_name(selected_item[i].get_name());
            selected_item[i - 1].set_price(selected_item[i].get_price());
            selected_item[i - 1].set_number(selected_item[i].get_number());
            selected_item[i].repaint();
        }
        
        return res;
    }

    public boolean is_empty()
    {
        if(selected_item[0].get_name() == "") return true;
        else return false;
    }

    public boolean is_full()
    {
        if(selected_item[4].get_name() != "") return true;
        else return false;
    }

    public int sum_all_item_price()
    {
        int sum = 0;

        for(int i = 0; i < 5; i++) {
            sum += selected_item[i].get_price() * selected_item[i].get_number();
        }

        return sum;
    }

    public void clear_list()
    {
        for(int i = 0; i < 5; i++) {
            selected_item[i].set_name("");
            selected_item[i].set_price(0);
            selected_item[i].set_number(0);
        }
    }
}

// class ChoicesList extends JPanel {
//     SelectedItemList selected_item_list;
//     Item[] choices;
//     JButton[] add_button;
//     JPanel[] choices_panel;

//     public ChoicesList(Item[] choices, SelectedItemList selected_item_list) 
//     {
//         super(new GridLayout(5,1,5,5));

//         this.choices = choices;

//         this.selected_item_list = selected_item_list;

//         add_button = new JButton[5];
//         choices_panel = new JPanel[5];

//         for(int i = 0; i < choices.length; i++) {
//             // add_button������
//             add_button[i] = new JButton("��ǉ�");
//             add_button[i].addActionListener(new ActionListener(){
//                 public void actionPerformed(ActionEvent e){
//                     int index = 0;
//                     JButton	btn = (JButton) e.getSource();
//                     for(int i = 0; i < 5; i++) {
//                         if(btn == add_button[i]) {
//                             index = i;
//                             break;
//                         }
//                     }
//                     selected_item_list.register_item(choices[index]);
//                     selected_item_list.repaint();
//                 }
//             });

//             // choices_item_panel������
//             choices_panel[i] = new JPanel(new BorderLayout());
//             choices_panel[i].add(choices[i], "Center");
//             choices_panel[i].add(add_button[i], "East");

//             // ���g�ɒǉ�
//             this.add(choices_panel[i]);
//         }
//     }
// }

// class AddItemScreen extends JPanel {
//     CardLayout layout;
//     JPanel container;

//     public AddItemScreen(SelectedItemList selected_item_list)
//     {
//         super();

//         container = this;
//         layout = new CardLayout();
//         container.setLayout(layout);
        
//         /*******************************
//          * �J�e�S����ʍ쐬
//          *******************************/
//         JPanel category_screen = new JPanel(new GridLayout(1, 2, 5, 5));
//         container.add(category_screen, "category_screen");
//         // ���ݕ���ʍ쐬
//         JPanel drink_screen = new JPanel(new BorderLayout());
//         category_screen.add(drink_screen);
//         //// �e�L�X�g�쐬
//         drink_screen.add(new JLabel("���ݕ�", JLabel.CENTER), "North");
//         //// ���ݕ����X�g�쐬
//         JPanel drink_list = new JPanel(new GridLayout(3, 1, 5, 5));
//         drink_screen.add(drink_list, "Center");
//         ////// �r�[���{�^��
//         JButton beer_button = new JButton("�r�[��");
//         drink_list.add(beer_button);
//         beer_button.addActionListener(new ActionListener(){
// 			public void actionPerformed(ActionEvent e){
//                 layout.show(container, "beer_screen");
// 			}
//         });
//         ////// �J�N�e���{�^��
//         JButton cocktail_button = new JButton("�J�N�e��");
//         drink_list.add(cocktail_button);
//         cocktail_button.addActionListener(new ActionListener(){
// 			public void actionPerformed(ActionEvent e){
//                 layout.show(container, "cocktail_screen");
// 			}
//         });
//         ////// �n�C�{�[���{�^��
//         JButton highball_button = new JButton("�n�C�{�[��");
//         drink_list.add(highball_button);
//         highball_button.addActionListener(new ActionListener(){
// 			public void actionPerformed(ActionEvent e){
//                 layout.show(container, "highball_screen");
// 			}
//         });
//         // ������ʍ쐬
//         JPanel dishes_screen = new JPanel(new BorderLayout());
//         category_screen.add(dishes_screen);
//         //// �e�L�X�g�쐬
//         dishes_screen.add(new JLabel("����", JLabel.CENTER), "North");
//         //// �������X�g�쐬
//         JPanel dishes_list = new JPanel(new GridLayout(3, 1, 5, 5));
//         dishes_screen.add(dishes_list, "Center");
//         ////// �Ă����{�^��
//         JButton yakitori_button = new JButton("�Ă���");
//         dishes_list.add(yakitori_button);
//         yakitori_button.addActionListener(new ActionListener(){
// 			public void actionPerformed(ActionEvent e){
//                 layout.show(container, "yakitori_screen");
// 			}
//         });
//         ////// �h�g�{�^��
//         JButton sasimi_button = new JButton("�h�g");
//         dishes_list.add(sasimi_button);
//         sasimi_button.addActionListener(new ActionListener(){
// 			public void actionPerformed(ActionEvent e){
//                 layout.show(container, "sasimi_screen");
// 			}
//         });
//         ////// �T���_�{�^��
//         JButton salad_button = new JButton("�T���_");
//         dishes_list.add(salad_button);
//         salad_button.addActionListener(new ActionListener(){
// 			public void actionPerformed(ActionEvent e){
//                 layout.show(container, "salad_screen");
// 			}
//         });

//         /*******************************
//          * �r�[����ʍ쐬
//          *******************************/
//         JPanel beer_screen = new JPanel(new BorderLayout());
//         container.add(beer_screen, "beer_screen");
//         // �e�L�X�g�쐬
//         beer_screen.add(new JLabel("�r�[����I��ł�������", JLabel.CENTER), "North");
//         // ���i���X�g�쐬
//         Item[] beer = new Item[5];
//         beer[0] = new Item("����", 390);
//         beer[1] = new Item("����", 490);
//         beer[2] = new Item("�吶", 750);
//         beer[3] = new Item("���r", 640);
//         beer[4] = new Item("�m���A���R�[���r�[��", 330);
//         ChoicesList beer_list = new ChoicesList(beer, selected_item_list);
//         beer_screen.add(beer_list, "Center");
//         // �J�e�S���I���ɖ߂�{�^���쐬
//         JButton category_button1 = new JButton("�J�e�S���I���ɖ߂�D");
//         category_button1.setPreferredSize(new Dimension(100,40));
//         category_button1.addActionListener(new ActionListener(){
// 			public void actionPerformed(ActionEvent e){
//                 layout.show(container, "category_screen");
// 			}
//         });
//         beer_screen.add(category_button1, "South");

//         /*******************************
//          * �J�N�e����ʍ쐬
//          *******************************/
//         JPanel cocktail_screen = new JPanel(new BorderLayout());
//         container.add(cocktail_screen, "cocktail_screen");
//         // �e�L�X�g�쐬
//         cocktail_screen.add(new JLabel("�J�N�e����I��ł�������", JLabel.CENTER), "North");
//         // ���i���X�g�쐬
//         Item[] cocktail = new Item[5];
//         cocktail[0] = new Item("�J�V�X�I�����W", 400);
//         cocktail[1] = new Item("�J�V�X�\�[�_", 400);
//         cocktail[2] = new Item("���X�R�~���[��", 400);
//         cocktail[3] = new Item("�W���g�j�b�N", 400);
//         cocktail[4] = new Item("�t�@�W�[�l�[�u��", 400);
//         ChoicesList cocktail_list = new ChoicesList(cocktail, selected_item_list);
//         cocktail_screen.add(cocktail_list, "Center");
//         // �J�e�S���I���ɖ߂�{�^���쐬
//         JButton category_button2 = new JButton("�J�e�S���I���ɖ߂�D");
//         category_button2.setPreferredSize(new Dimension(100,40));
//         category_button2.addActionListener(new ActionListener(){
// 			public void actionPerformed(ActionEvent e){
//                 layout.show(container, "category_screen");
// 			}
//         });
//         cocktail_screen.add(category_button2, "South");

//         /*******************************
//          * �n�C�{�[����ʍ쐬
//          *******************************/
//         JPanel highball_screen = new JPanel(new BorderLayout());
//         container.add(highball_screen, "highball_screen");
//         // �e�L�X�g�쐬
//         highball_screen.add(new JLabel("�n�C�{�[����I��ł�������", JLabel.CENTER), "North");
//         // ���i���X�g�쐬
//         Item[] highball = new Item[5];
//         highball[0] = new Item("�䂸�n�C�{�[��", 450);
//         highball[1] = new Item("�W���W���[�n�C�{�[��", 450);
//         highball[2] = new Item("�������n�C�{�[��", 450);
//         highball[3] = new Item("�L�E�C�n�C�{�[��", 450);
//         highball[4] = new Item("�R�[���n�C�{�[��", 450);
//         ChoicesList highball_list = new ChoicesList(highball, selected_item_list);
//         highball_screen.add(highball_list, "Center");
//         // �J�e�S���I���ɖ߂�{�^���쐬
//         JButton category_button3 = new JButton("�J�e�S���I���ɖ߂�D");
//         category_button3.setPreferredSize(new Dimension(100,40));
//         category_button3.addActionListener(new ActionListener(){
// 			public void actionPerformed(ActionEvent e){
//                 layout.show(container, "category_screen");
// 			}
//         });
//         highball_screen.add(category_button3, "South");

//         /*******************************
//          * �Ă�����ʍ쐬
//          *******************************/
//         JPanel yakitori_screen = new JPanel(new BorderLayout());
//         container.add(yakitori_screen, "yakitori_screen");
//         // �e�L�X�g�쐬
//         yakitori_screen.add(new JLabel("�Ă�����I��ł��������D", JLabel.CENTER), "North");
//         // ���i���X�g�쐬
//         Item[] yakitori = new Item[5];
//         yakitori[0] = new Item("��", 130);
//         yakitori[1] = new Item("����", 130);
//         yakitori[2] = new Item("�؃o��", 130);
//         yakitori[3] = new Item("������", 130);
//         yakitori[4] = new Item("������", 130);
//         ChoicesList yakitori_list = new ChoicesList(yakitori, selected_item_list);
//         yakitori_screen.add(yakitori_list, "Center");
//         // �J�e�S���I���ɖ߂�{�^���쐬
//         JButton category_button4 = new JButton("�J�e�S���I���ɖ߂�D");
//         yakitori_screen.add(category_button4, "South");
//         category_button4.setPreferredSize(new Dimension(100,40));
//         category_button4.addActionListener(new ActionListener(){
// 			public void actionPerformed(ActionEvent e){
//                 layout.show(container, "category_screen");
// 			}
//         });

//         /*******************************
//          * �h�g��ʍ쐬
//          *******************************/
//         JPanel sasimi_screen = new JPanel(new BorderLayout());
//         container.add(sasimi_screen, "sasimi_screen");
//         // �e�L�X�g�쐬
//         sasimi_screen.add(new JLabel("�h�g��I��ł��������D", JLabel.CENTER), "North");
//         // ���i���X�g�쐬
//         Item[] sasimi = new Item[5];
//         sasimi[0] = new Item("�܂���", 580);
//         sasimi[1] = new Item("�T�[����", 480);
//         sasimi[2] = new Item("����", 480);
//         sasimi[3] = new Item("����", 480);
//         sasimi[4] = new Item("����", 480);
//         ChoicesList sasimi_list = new ChoicesList(sasimi, selected_item_list);
//         sasimi_screen.add(sasimi_list, "Center");
//         // �J�e�S���I���ɖ߂�{�^���쐬
//         JButton category_button5 = new JButton("�J�e�S���I���ɖ߂�D");
//         sasimi_screen.add(category_button5, "South");
//         category_button5.setPreferredSize(new Dimension(100,40));
//         category_button5.addActionListener(new ActionListener(){
// 			public void actionPerformed(ActionEvent e){
//                 layout.show(container, "category_screen");
// 			}
//         });
        
//         /*******************************
//          * �T���_��ʍ쐬
//          *******************************/
//         JPanel salad_screen = new JPanel(new BorderLayout());
//         container.add(salad_screen, "salad_screen");
//         // �e�L�X�g�쐬
//         salad_screen.add(new JLabel("�T���_��I��ł��������D", JLabel.CENTER), "North");
//         // ���i���X�g�쐬
//         Item[] salad = new Item[5];
//         salad[0] = new Item("�|�e�g�T���_", 380);
//         salad[1] = new Item("�g�}�g�T���_", 380);
//         salad[2] = new Item("�C���T���_", 380);
//         salad[3] = new Item("�O���[���T���_", 380);
//         salad[4] = new Item("�V�[�U�[�T���_", 380);
//         ChoicesList salad_list = new ChoicesList(salad, selected_item_list);
//         salad_screen.add(salad_list, "Center");
//         // �J�e�S���I���ɖ߂�{�^���쐬
//         JButton category_button6 = new JButton("�J�e�S���I���ɖ߂�D");
//         salad_screen.add(category_button6, "South");
//         category_button6.setPreferredSize(new Dimension(100,40));
//         category_button6.addActionListener(new ActionListener(){
// 			public void actionPerformed(ActionEvent e){
//                 layout.show(container, "category_screen");
// 			}
//         });
//     }

//     public void show_category_screen()
//     {
//         layout.show(container, "category_screen");

//         return;
//     }
// }

class BarOrderFrame extends JFrame implements ActionListener{
    Container container;
    Item test_item;
    // CardLayout container_layout;
    // SelectedItemList selected_item_list;
    // JLabel total_cost_label;
    
    // public BarOrderFrame (String title) {
    //     super(title);

    //     container = this.getContentPane();
    //     container.setBackground(Color.white);
    //     container.setForeground(Color.green);
    //     container_layout = new CardLayout();
    //     container.setLayout(container_layout);

    //     // selected_item_list�쐬
    //     selected_item_list = new SelectedItemList();

    //     /*****************************
    //      * ���i�I����ʍ쐬
    //      *****************************/
    //     JPanel selecting_item_screen = new JPanel(new GridLayout(1, 2, 5, 5));
    //     container.add(selecting_item_screen, "selecting_item_screen");
    //     // ���i�ǉ���ʍ쐬
    //     AddItemScreen add_item_screen = new AddItemScreen(selected_item_list);
    //     selecting_item_screen.add(add_item_screen);
    //     // �I�񂾏��i��ʍ쐬
    //     JPanel selected_item_screen = new JPanel(new BorderLayout());
    //     selecting_item_screen.add(selected_item_screen);
    //     //// �e�L�X�g�쐬
    //     selected_item_screen.add(new JLabel("�I�񂾏��i", JLabel.CENTER), "North");
    //     //// selected_item_list��ʂɒǉ�
    //     selected_item_screen.add(selected_item_list, "Center");
    //     //// �����{�^���쐬
    //     JButton confirm_order_button = new JButton("��������");
    //     confirm_order_button.setPreferredSize(new Dimension(100,40));
    //     confirm_order_button.addActionListener(new ActionListener(){
	// 		public void actionPerformed(ActionEvent e){
    //             if(selected_item_list.is_empty()) {

    //             }
    //             else {
    //                 container_layout.show(container, "confirm_order_screen");
    //                 total_cost_label.setText("���v���z�� " + selected_item_list.sum_all_item_price() + "�~�ł�");
    //             }
	// 		}
    //     });
    //     selected_item_screen.add(confirm_order_button, "South");
    //     /*****************************
    //      * �����m�F��ʍ쐬
    //      *****************************/
    //     JPanel confirm_order_screen = new JPanel(new GridLayout(3, 1, 5, 5));
    //     container.add(confirm_order_screen, "confirm_order_screen");
    //     // ���v���z�́E�E�E�~�ł�
    //     total_cost_label = new JLabel("���v���z�́E�E�E�~�ł�", JLabel.CENTER);
    //     confirm_order_screen.add(total_cost_label);
    //     // �������܂���
    //     confirm_order_screen.add(new JLabel("�������܂���", JLabel.CENTER));
    //     // �u�͂��v�u�������v�{�^���쐬
    //     JPanel yes_no_panel = new JPanel(new GridLayout(1, 2, 5, 5));
    //     confirm_order_screen.add(yes_no_panel);
    //     //// �u�͂��v�{�^��
    //     JButton yes_button = new JButton("�͂�");
    //     yes_no_panel.add(yes_button);
    //     yes_button.addActionListener(new ActionListener(){
	// 		public void actionPerformed(ActionEvent e){
    //             container_layout.show(container, "selecting_item_screen");
    //             selected_item_list.clear_list();
    //             add_item_screen.show_category_screen();
	// 		}
    //     });
    //     //// �u�������v�{�^��
    //     JButton no_button = new JButton("������");
    //     yes_no_panel.add(no_button);
    //     no_button.addActionListener(new ActionListener(){
	// 		public void actionPerformed(ActionEvent e){
    //             container_layout.show(container, "selecting_item_screen");
	// 		}
    //     });

    //     this.setLocation(200, 100);
    //     this.setSize(640, 480);
    //     this.setVisible(true);
    // }

    public BarOrderFrame (String title)
    {
        super(title);

        container = this.getContentPane();
        container.setBackground(Color.white);
        container.setForeground(Color.green);
        //container.setLayout(new GridLayout(3,3,5,5));

        test_item = new Item("test", 100);
        test_item.set_button_param("�ǉ�", this);
        container.add(test_item);

        this.setLocation(200, 100);
        this.setSize(640, 480);
        this.setVisible(true);
    }

    public void actionPerformed(ActionEvent e){
        test_item.set_name("Push");
    }

    public static void main(String[] args) {
		BarOrderFrame frame = new BarOrderFrame("����������");
	}
}