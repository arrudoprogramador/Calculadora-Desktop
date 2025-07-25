import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Calculadora extends JFrame implements ActionListener {

    private JTextField display; // Campo que mostra valor e resultado
    private JButton[] numeros = new JButton[10];
    private JButton adicao, subtracao, multiplicacao, divisao, igual, limpar, porcent, borracha, copy, ponto;
    private double valor1, valor2;
    private char operador;

    public Calculadora() {
        // Display de resultados
        display = new JTextField();
        display.setEditable(false); // Não editável
        display.setFont(new Font("Arial", Font.PLAIN, 23));
        display.setHorizontalAlignment(SwingConstants.RIGHT);
        display.setBackground(Color.BLACK);
        display.setForeground(Color.WHITE);

        // Painel dos botões
        JPanel painel = new JPanel();
        painel.setLayout(new GridLayout(5, 4, 5, 5)); // 5x4
        painel.setBackground(Color.BLACK);

        // Botões de números
        for (int i = 0; i < 10; i++) {
            numeros[i] = new JButton(String.valueOf(i));
            numeros[i].setFont(new Font("Arial", Font.BOLD, 20));
            numeros[i].setBackground(Color.DARK_GRAY);
            numeros[i].setForeground(Color.WHITE);
            numeros[i].addActionListener(this);
        }

        // Botões de operações
        adicao = new JButton("+");
        subtracao = new JButton("-");
        multiplicacao = new JButton("X");
        divisao = new JButton("/");
        igual = new JButton("=");
        limpar = new JButton("AC");
        porcent = new JButton("%");
        ponto = new JButton(".");
        borracha = new JButton("<x-");
        
        copy = new JButton("©");
        copy.setFont(new Font("Arial", Font.BOLD, 20));
        copy.setBackground(Color.DARK_GRAY);
        copy.setForeground(Color.WHITE);
        copy.addActionListener(e -> {}); // Listener vazio
        
        ponto = new JButton(".");
        ponto.setFont(new Font("Arial", Font.BOLD, 20));
        ponto.setBackground(Color.DARK_GRAY);
        ponto.setForeground(Color.WHITE);
        

        // Configuração estética dos botões
        JButton[] operadores = {adicao, subtracao, multiplicacao, divisao, igual, limpar, porcent, borracha};
        for (JButton operador : operadores) {
            operador.setFont(new Font("Arial", Font.BOLD, 20));
            operador.setBackground(new Color(0xfca50d));
            operador.setForeground(Color.WHITE);
            operador.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
            operador.addActionListener(this);
        }
        
        //Estética dos demais
        JButton[] demais = {copy, ponto};
        for (JButton demai : demais) {
        	demai.setFont(new Font("Arial", Font.BOLD, 20));
        	demai.setBackground(Color.DARK_GRAY);
        	demai.setForeground(Color.WHITE);
        	demai.addActionListener(this);
        }

        // Adiciona os botões ao painel
        painel.add(limpar); painel.add(borracha); painel.add(porcent); painel.add(divisao);
        painel.add(numeros[7]); painel.add(numeros[8]); painel.add(numeros[9]); painel.add(multiplicacao);
        painel.add(numeros[4]); painel.add(numeros[5]); painel.add(numeros[6]); painel.add(subtracao); 
        painel.add(numeros[1]); painel.add(numeros[2]); painel.add(numeros[3]); painel.add(adicao);
        painel.add(copy); painel.add(numeros[0]); painel.add(ponto); painel.add(igual);

        add(display, BorderLayout.NORTH);
        add(painel);

        // Janela
        setTitle("Sua Calculadora Apple");
        setSize(300, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String comando = e.getActionCommand();

        try {
            if ((comando.charAt(0) >= '0' && comando.charAt(0) <= '9') || comando.equals(".")) {
                display.setText(display.getText() + comando);

            } else if (comando.equals("AC")) {
                display.setText("");
                valor1 = valor2 = 0;

            } else if (comando.equals("=")) {
                if (!display.getText().isEmpty()) {
                    valor2 = Double.parseDouble(display.getText());
                    switch (operador) {
                        case '+': display.setText(String.valueOf(valor1 + valor2)); break;
                        case '-': display.setText(String.valueOf(valor1 - valor2)); break;
                        case 'X': display.setText(String.valueOf(valor1 * valor2)); break;
                        case '/': display.setText(valor2 != 0 ? String.valueOf(valor1 / valor2) : "Erro"); break;
                        case '%': display.setText(String.valueOf((valor1 * valor2) / 100)); break;
                    }
                }

            } else if (comando.equals("<x-")) {
                String texto = display.getText();
                if (!texto.isEmpty()) {
                    display.setText(texto.substring(0, texto.length() - 1));
                }
            } else {
                if (!display.getText().isEmpty()) {
                    valor1 = Double.parseDouble(display.getText());
                    operador = comando.charAt(0);
                    display.setText("");
                }
            }
        } catch (NumberFormatException ex) {
            display.setText("Erro");
        }
    }
    
    public static void main(String[] args) {
        Calculadora Calculadora = new Calculadora();
        
    }
}
