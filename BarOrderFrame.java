import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

// class AddItemButton extends JButton {

// }

class Item extends JLabel {
    private String name;
    private int price;

    public Item(String name, int price)
    {
        super();

        String label_text;
        
        if(name == "") {
            label_text = "";
        }
        else {
            label_text = name + ": " + price + "â~";
        }

        this.setText(label_text);
        this.setHorizontalAlignment(JLabel.CENTER);
        this.setForeground(Color.black);
        this.setFont(new Font("MSÅ@ÉSÉVÉbÉN", Font.PLAIN, 16));
        
        this.name = name;
        this.price = price;
    }

    public void set_name(String name)
    {
        String label_text;
        
        if(name == "") {
            label_text = "";
        }
        else {
            label_text = name + ": " + price + "â~";
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
        else {
            label_text = name + ": " + price + "â~";
        }

        this.setText(label_text);

        this.price = price;

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
}



class SelectedItemPanel extends JPanel {
    Item[] selected_item;
    JButton[] delete_button;
    JPanel[] selected_item_panel;

    public SelectedItemPanel ()
    {
        super(new GridLayout(5,1,5,5));

        selected_item = new Item[5];
        delete_button = new JButton[5];
        selected_item_panel = new JPanel[5];

        for(int i = 0; i < 5; i++) {
            // selected_itemèâä˙âª
            selected_item[i] = new Item("", 0);

            // delete_buttonèâä˙âª
            delete_button[i] = new JButton("çÌèú");
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
                    for(int i = index; i < 4; i++) {
                        selected_item[i].set_name(selected_item[(i + 1)].get_name());
                        selected_item[i].set_price(selected_item[(i + 1)].get_price());
                        selected_item_panel[i].repaint();
                    }
                    selected_item[4].set_name("");
                    selected_item[4].set_price(0);
                    selected_item_panel[4].repaint();
                }
            });

            // selected_item_panelèâä˙âª
            selected_item_panel[i] = new JPanel(new BorderLayout());
            selected_item_panel[i].add("Center", selected_item[i]);
            selected_item_panel[i].add("East", delete_button[i]);

            // é©êgÇ…í«â¡
            this.add(selected_item_panel[i]);
        }
    }

    public void register_item(Item item)
    {
        for(int i = 0; i < 5; i++) {
            if(selected_item[i].get_name() == "") {
                selected_item[i].set_name(item.get_name());
                selected_item[i].set_price(item.get_price());
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
            sum += selected_item[i].get_price();
        }

        return sum;
    }

    public void clear_list()
    {
        for(int i = 0; i < 5; i++) {
            selected_item[i] = new Item("", 0);
        }
    }
}

class BarOrderFrame extends JFrame {
    Container container;
    SelectedItemPanel selected_item_list;

    public BarOrderFrame (String title) {
        super(title);

        container = this.getContentPane();
        container.setBackground(Color.white);
        container.setForeground(Color.green);
        container.setLayout(new GridLayout(3, 1, 5, 5));

        selected_item_list = new SelectedItemPanel();
        container.add(selected_item_list);

        JButton button_1 = new JButton("Button1");
        button_1.setPreferredSize(new Dimension(10,10));
		container.add(button_1);
		button_1.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
                Item item1 = new Item("ÉeÉXÉg", 100);
                selected_item_list.register_item(item1);
			}
        });

        JButton button_2 = new JButton("Button1");
        button_2.setPreferredSize(new Dimension(10,10));
		container.add(button_2);
		button_2.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
                Item item1 = new Item("test", 200);
                selected_item_list.register_item(item1);
			}
        });

        this.setLocation(200, 100);
        this.setSize(640, 320);
        this.setVisible(true);
    }
    public static void main(String[] args) {
		BarOrderFrame frame = new BarOrderFrame("ãèéâÆíçï∂");
	}
}