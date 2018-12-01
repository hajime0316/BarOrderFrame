import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

class Item extends JLabel {
    String name;
    int price;
    int number;

    public Item()
    {
        super();

        String label_text;

        label_text = "";

        this.setText(label_text);
        this.setHorizontalAlignment(JLabel.CENTER);
        this.setForeground(Color.black);
//        this.setFont(new Font("MS　ゴシック", Font.PLAIN, 16));
        
        this.name = "";
        this.price = 0;
        this.number = 0;
    }

    public Item(String name, int price)
    {
        super();

        String label_text;
        
        if(name == "") {
            label_text = "";
        }
        else {
            label_text = name + ": " + price + "円";
        }

        this.setText(label_text);
        this.setHorizontalAlignment(JLabel.CENTER);
        this.setForeground(Color.black);
//        this.setFont(new Font("MS　ゴシック", Font.PLAIN, 16));
        
        this.name = name;
        this.price = price;
        this.number = 0;
    }

    public Item(String name, int price, int number)
    {
        super();

        String label_text;
        
        if(name == "") {
            label_text = "";
        }
        else if(number <= 0){
            label_text = name + ": " + price + "円";
        }
        else {
            label_text = name + ": " + price + "円 | " + number + "個";
        }

        this.setText(label_text);
        this.setHorizontalAlignment(JLabel.CENTER);
        this.setForeground(Color.black);
//        this.setFont(new Font("MS　ゴシック", Font.PLAIN, 16));
        
        this.name = name;
        this.price = price;
        this.number = number;
    }

    public void set_name(String name)
    {
        String label_text;
        
        if(name == "") {
            label_text = "";
        }
        else if(number <= 0){
            label_text = name + ": " + price + "円";
        }
        else {
            label_text = name + ": " + price + "円 | " + number + "個";
        }

        this.setText(label_text);

        this.name = name;

        return;
    }

    public void set_price(int price)
    {
        String label_text;
        
        if(name == "") {
            label_text = "";
        }
        else if(number <= 0){
            label_text = name + ": " + price + "円";
        }
        else {
            label_text = name + ": " + price + "円 | " + number + "個";
        }

        this.setText(label_text);

        this.price = price;

        return;
    }

    public void set_number(int number)
    {
        String label_text;
        
        if(name == "") {
            label_text = "";
        }
        else if(number <= 0){
            label_text = name + ": " + price + "円";
        }
        else {
            label_text = name + ": " + price + "円 | " + number + "個";
        }

        this.setText(label_text);

        this.number = number;

        return;
    }

    public void increment_number()
    {
        set_number(number + 1);
        return;
    }

    public void decrement_number()
    {
        set_number(number - 1);
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

class SelectedItemList extends JPanel {
    Item[] selected_item;
    JButton[] delete_button;
    JPanel[] selected_item_panel;

    public SelectedItemList ()
    {
        super(new GridLayout(5,1,5,5));

        selected_item = new Item[5];
        delete_button = new JButton[5];
        selected_item_panel = new JPanel[5];

        for(int i = 0; i < 5; i++) {
            // selected_item初期化
            selected_item[i] = new Item();

            // delete_button初期化
            delete_button[i] = new JButton("削除");
            delete_button[i].addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent e){
                    int index = 0;

                    JButton	btn = (JButton) e.getSource();
                    for(int i = 0; i < 5; i++) {
                        if(btn == delete_button[i]) {
                            index = i;
                            break;
                        }
                    }

                    if(selected_item[index].get_number() >= 2) {
                        selected_item[index].decrement_number();
                        selected_item_panel[index].repaint();
                    }
                    else {
                        for(int i = index; i < 4; i++) {
                            selected_item[i].set_name(selected_item[(i + 1)].get_name());
                            selected_item[i].set_price(selected_item[(i + 1)].get_price());
                            selected_item[i].set_number(selected_item[(i + 1)].get_number());
                            selected_item_panel[i].repaint();
                        }
                        selected_item[4].set_name("");
                        selected_item[4].set_price(0);
                        selected_item_panel[4].repaint();
                    }
                }
            });

            // selected_item_panel初期化
            selected_item_panel[i] = new JPanel(new BorderLayout());
            selected_item_panel[i].add(selected_item[i], "Center");
            selected_item_panel[i].add(delete_button[i], "East");

            // 自身に追加
            this.add(selected_item_panel[i]);
        }
    }

    public void register_item(Item item)
    {
        for(int i = 0; i < 5; i++) {
            if(selected_item[i].get_name() == "") {
                selected_item[i].set_name(item.get_name());
                selected_item[i].set_price(item.get_price());
                selected_item[i].set_number(1);
                selected_item_panel[i].repaint();
                break;
            }
            else if(selected_item[i].get_name() == item.get_name()) {
                selected_item[i].increment_number();
                selected_item_panel[i].repaint();
                break;
            }
        }

        return;
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
            selected_item[i] = new Item();
        }
    }
}

class ChoicesList extends JPanel {
    SelectedItemList selected_item_panel;
    Item[] choices;
    JButton[] add_button;
    JPanel[] choices_panel;

    public ChoicesList(Item[] choices, SelectedItemList selected_item_panel) 
    {
        super(new GridLayout(5,1,5,5));

        this.choices = choices;

        this.selected_item_panel = selected_item_panel;

        add_button = new JButton[5];
        choices_panel = new JPanel[5];

        for(int i = 0; i < choices.length; i++) {
            // add_button初期化
            add_button[i] = new JButton("追加");
            add_button[i].addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent e){
                    int index = 0;
                    JButton	btn = (JButton) e.getSource();
                    for(int i = 0; i < 5; i++) {
                        if(btn == add_button[i]) {
                            index = i;
                            break;
                        }
                    }
                    selected_item_panel.register_item(choices[index]);
                    selected_item_panel.repaint();
                }
            });

            // choices_item_panel初期化
            choices_panel[i] = new JPanel(new BorderLayout());
            choices_panel[i].add(choices[i], "Center");
            choices_panel[i].add(add_button[i], "East");

            // 自身に追加
            this.add(choices_panel[i]);
        }
    }
}

class BarOrderFrame extends JFrame {
    Container container;
    CardLayout container_layout;
    SelectedItemList selected_item_list;
    ChoicesList cocktail_list;

    public BarOrderFrame (String title) {
        super(title);

        container = this.getContentPane();
        container.setBackground(Color.white);
        container.setForeground(Color.green);
        container_layout = new CardLayout();
        container.setLayout(container_layout);

        selected_item_list = new SelectedItemList();

        // カテゴリ選択画面作成
        JPanel category_screen = new JPanel(new GridLayout(5,1,5,5));

        category_screen.add(new JLabel("カテゴリを選択してください．"));

        JButton cocktail_button = new JButton("カクテル");
        category_screen.add(cocktail_button);
        cocktail_button.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
                container_layout.show(container, "cocktail_screen");
			}
        });

        JButton selected_item_button = new JButton("カートに移動");
        category_screen.add(selected_item_button);
        selected_item_button.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
                container_layout.show(container, "selected_item_screen");
			}
        });

        container.add(category_screen, "category_screen");

        // 品目選択(カクテル)画面作成
        JPanel cocktail_screen = new JPanel(new BorderLayout());

        cocktail_screen.add(new JLabel("品目(カクテル)を選択してください．", JLabel.CENTER), "North");

        Item[] cocktail = new Item[5];
        cocktail[0] = new Item("a", 100);
        cocktail[1] = new Item("b", 200);
        cocktail[2] = new Item("c", 300);
        cocktail[3] = new Item("d", 400);
        cocktail[4] = new Item("e", 500);
        cocktail_list = new ChoicesList(cocktail, selected_item_list);
        cocktail_screen.add(cocktail_list, "Center");

        JPanel choices_screen_controller = new JPanel(new GridLayout(1,2,5,5));
        JButton category_button = new JButton("カテゴリ選択に戻る．");
        category_button.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
                container_layout.show(container, "category_screen");
			}
        });
        choices_screen_controller.add(category_button);
        choices_screen_controller.add(selected_item_button);
        cocktail_screen.add(choices_screen_controller, "South");

        container.add(cocktail_screen, "cocktail_screen");

        // 選済み品目画面作成
        JPanel selected_item_screen = new JPanel(new BorderLayout());

        selected_item_screen.add(new JLabel("カート", JLabel.CENTER), "North");

        selected_item_screen.add(selected_item_list, "Center");

        JPanel selected_item_screen_controller = new JPanel(new GridLayout(1,2,5,5));
        JButton confirm_order_button = new JButton("注文");
        confirm_order_button.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
                container_layout.show(container, "confirm_order_screen");
			}
        });
        selected_item_screen_controller.add(category_button);
        selected_item_screen_controller.add(confirm_order_button);
        selected_item_screen.add(selected_item_screen_controller, "South");

        container.add(selected_item_screen, "selected_item_screen");
        
        // 注文確認画面作成

        // JButton button_1 = new JButton("Button1");
        // button_1.setPreferredSize(new Dimension(10,10));
		// container.add(button_1);
		// button_1.addActionListener(new ActionListener(){
		// 	public void actionPerformed(ActionEvent e){
        //         Item item1 = new Item("テスト", 100);
        //         selected_item_list.register_item(item1);
		// 	}
        // });

        // JButton button_2 = new JButton("Button1");
        // button_2.setPreferredSize(new Dimension(10,50));
		// container.add(button_2, "South");
		// button_2.addActionListener(new ActionListener(){
		// 	public void actionPerformed(ActionEvent e){
        //         Item item1 = new Item("test", 200);
        //         selected_item_list.register_item(item1);
		// 	}
        // });

        this.setLocation(200, 100);
        this.setSize(640, 480);
        this.setVisible(true);
    }
    public static void main(String[] args) {
		BarOrderFrame frame = new BarOrderFrame("居酒屋注文");
	}
}