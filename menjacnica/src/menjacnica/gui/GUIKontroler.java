package menjacnica.gui;

import menjacnica.Menjacnica;
import menjacnica.gui.models.MenjacnicaTableModel;
import menjacnica.interfejs.MenjacnicaInterface;
import menjacnica.Valuta;

import java.awt.EventQueue;
import java.io.File;
import java.util.LinkedList;
import java.util.List;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

public class GUIKontroler {

	private static MenjacnicaInterface sistem;
	private static MenjacnicaGUI glavniProzor;
	

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					sistem = new Menjacnica();
					glavniProzor = new MenjacnicaGUI();
					glavniProzor.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});

	}
	
	public static List<Valuta> vratiKursnuListu() {
		return sistem.vratiKursnuListu();
	}

	public static void obrisiValutu(Valuta valuta) {
		try {
			glavniProzor.sistem.obrisiValutu(valuta);

			glavniProzor.prikaziSveValute();
		} catch (Exception e1) {
			JOptionPane.showMessageDialog(glavniProzor, e1.getMessage(),
					"Greska", JOptionPane.ERROR_MESSAGE);
		}
	}

	public static void unesiKurs(String naziv, String skNaziv, int sif,
			String kupovni, String srednji, String prodajni) {
		try {
			Valuta valuta = new Valuta();

			valuta.setNaziv(naziv);
			valuta.setSkraceniNaziv(skNaziv);
			valuta.setSifra((Integer) (sif));
			valuta.setProdajni(Double.parseDouble(prodajni));
			valuta.setKupovni(Double.parseDouble(kupovni));
			valuta.setSrednji(Double.parseDouble(srednji));

			glavniProzor.sistem.dodajValutu(valuta);

			glavniProzor.prikaziSveValute();

		} catch (Exception e1) {
			JOptionPane.showMessageDialog(glavniProzor, e1.getMessage(),
					"Greska", JOptionPane.ERROR_MESSAGE);
		}
	}

	public static double izvrsiZamenu(Valuta valuta, boolean prodaja,
			String iznos) {
		return sistem.izvrsiTransakciju(valuta, prodaja,
				Double.parseDouble(iznos));
	}

	public static void ugasiAplikaciju() {
		int opcija = JOptionPane.showConfirmDialog(glavniProzor,
				"Da li ZAISTA zelite da izadjete iz apliacije", "Izlazak",
				JOptionPane.YES_NO_OPTION);

		if (opcija == JOptionPane.YES_OPTION)
			System.exit(0);
	}

	public static void prikaziAboutProzor() {
		JOptionPane.showMessageDialog(glavniProzor,
				"Autor: Bojan Tomic, Verzija 1.0", "O programu Menjacnica",
				JOptionPane.INFORMATION_MESSAGE);
	}

	public static void prikaziDodajKursGUI() {
		DodajKursGUI prozor = new DodajKursGUI(glavniProzor);
		prozor.setLocationRelativeTo(null);
		prozor.setVisible(true);
	}

	public static void prikaziObrisiKursGUI(Valuta valuta) {

		ObrisiKursGUI prozor = new ObrisiKursGUI(glavniProzor, valuta);
		prozor.setLocationRelativeTo(null);
		prozor.setVisible(true);
	}
	public static void prikaziIzvrsiZamenuGUI(Valuta valuta) {

		IzvrsiZamenuGUI prozor = new IzvrsiZamenuGUI(glavniProzor, valuta);
		prozor.setLocationRelativeTo(null);
		prozor.setVisible(true);
	}

	public static void ucitajIzFajla() {
		try {
			JFileChooser fc = new JFileChooser();
			int returnVal = fc.showOpenDialog(glavniProzor);

			if (returnVal == JFileChooser.APPROVE_OPTION) {
				File file = fc.getSelectedFile();
				sistem.ucitajIzFajla(file.getAbsolutePath());
				glavniProzor.prikaziSveValute();
			}
		} catch (Exception e1) {
			JOptionPane.showMessageDialog(glavniProzor, e1.getMessage(),
					"Greska", JOptionPane.ERROR_MESSAGE);
		}
	}

	public static void sacuvajUFajl() {
		try {
			JFileChooser fc = new JFileChooser();
			int returnVal = fc.showSaveDialog(glavniProzor);

			if (returnVal == JFileChooser.APPROVE_OPTION) {
				File file = fc.getSelectedFile();

				sistem.sacuvajUFajl(file.getAbsolutePath());
			}
		} catch (Exception e1) {
			JOptionPane.showMessageDialog(glavniProzor, e1.getMessage(),
					"Greska", JOptionPane.ERROR_MESSAGE);
		}
	}

	
}
