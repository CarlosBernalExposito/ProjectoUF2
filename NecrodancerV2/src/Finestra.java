import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.GridLayout;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

/**
 * Finestra Crea una finestra gr�fica. Dintre tindr� el taulell i alguns textos
 * informatius. Basicament aquesta classe separa el propi taulell (la matriu) de
 * la resta de coses de l'interficie gr�fica. Creat per Marc Albareda
 *
 */
public class Finestra extends JFrame {

	/**
	 * De moment te etiquetes. En algun moment li puc posar coses xules com una
	 * pantalla d'intro, high scores, credits, musica
	 */

	/**
	 * No se si fer que Taulell sigui privat i l'usuari nom�s interactui amb
	 * Finestra o tenir-ho separat. Hm...
	 */

	private Taulell taulell;
	private Taulell taulell2;
	private boolean segontaulell=false;
	private boolean actetiquetes = false;
	private String[] etiquetes = { "" };
	/// pots posar tantes etiquetes com vulguis eh, no est�s limitat a 3.
	private JPanel labelpanel = new JPanel(new GridLayout(0, 1, 5, 5));
	private JPanel tpanel = new JPanel(new GridLayout(0, 2, 5, 5));
	private char ultimChar;  //ultim caracter apretat. Es mantindr� fins que se n'apreti un altre
	private char actualChar; //caracter actual apretat. Consultarlo fara que s'esborri

	public Finestra(Taulell t) {
		taulell = t;
		inici();
	}
	
	public Finestra(Taulell t, Taulell t2) {
		taulell = t;
		taulell2 = t2;
		segontaulell = true;
		inici();
	}

	private void inici() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		if (segontaulell) {
			tpanel.add(taulell);
			tpanel.add(taulell2);
			add(tpanel);
		}else add(taulell);

		if (actetiquetes) {
			for (String s : etiquetes) {
				JLabel etiq = new JLabel(s);
				labelpanel.add(etiq);
			}
		}
		add(labelpanel, BorderLayout.LINE_END);
		labelpanel.setBorder(new EmptyBorder(10,10,10,10));

		setSize(700, 400);
		setLocation(100, 100);
		setVisible(true);
		taulell.addComponentListener(taulell.cl);
		

	    addKeyListener(ka);

	}

	private void renovar() {
		labelpanel.removeAll();
		if (actetiquetes) {
			for (String s : etiquetes) {
				JLabel etiq = new JLabel(s);
				labelpanel.add(etiq);
			}

		}
		labelpanel.repaint();
		labelpanel.revalidate();
		// for�a una reinicialitzacio de tots els quadrats de taulell com a
		// efecte secundari

	}

	public boolean isActetiquetes() {
		return actetiquetes;
	}

	public void setActetiquetes(boolean actetiquetes) {
		this.actetiquetes = actetiquetes;
		if (!actetiquetes)
			renovar();
	}

	public String[] getEtiquetes() {
		return etiquetes;
	}

	public void setEtiquetes(String[] etiquetes) {
		this.etiquetes = etiquetes;
		renovar();
	}
	
	public char getUltimChar() {
		return ultimChar;
	}

	public char getActualChar() {
		char tempc = actualChar;
		actualChar = '0';
		return tempc;
	}

	
	//Integracio del teclat. Ara pot detectar pulsacions de tecles sense necessitat de l'intro i l'escanner.
	public KeyAdapter ka = new KeyAdapter() {
		  public void keyPressed(KeyEvent e)
		  {
		    NecrodancerV2.pDir = e.getKeyChar();
	
		  }
	};

}

