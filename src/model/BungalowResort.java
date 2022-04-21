package model;

import java.io.*;
import java.util.*;

public class BungalowResort {
	private String name;
	private LinkedList<Bungalow> bungalows;

	public BungalowResort(String name) throws ResortException {
		setName(name);
		bungalows = new LinkedList<Bungalow>();
	}

	// ----------------------------------- getter ------------------------
	public String getName() {
		return name;
	}

	// ----------------------------------- setter ------------------------
	public void setName(String name) throws ResortException {
		if (name != null)
			this.name = name;
		else
			throw new ResortException("Null-Referenz fuer BungalowResort.setName!");
	}

	// ----------------------------------- others ------------------------
	public void addBungalow(Bungalow bungalow) throws ResortException {
		if (bungalow != null)
			if (bungalows.size() < 100)
				if (!bungalows.contains(bungalow))
					bungalows.add(bungalow);
				else
					throw new ResortException("Dieser Bungalow steht bereits in der Anlage!!");
			else
				throw new ResortException("Es stehen bereits 100 Bungalows in der Anlage!!");
		else
			throw new ResortException("Null-Referenz fuer BungalowResort.addBungalow!");
	}

	public double berechneTagesertrag() {
		double ertrag = 0.;
		for (Bungalow b : bungalows)
			ertrag += b.berechneTagesertrag();
		return ertrag;
	}

	public int berechneAnzBettenBelegt() {
		int anz = 0;
		for (Bungalow b : bungalows) {
			if (b instanceof WohnBungalow)
				if (!b.isFrei())
					anz += ((WohnBungalow) b).getBetten();
		}
		return anz;
	}

	public void removeBungalow(int pos) throws ResortException {
		if (pos >= 0 && pos < bungalows.size()) {
			bungalows.remove(pos);
		} else
			throw new ResortException("Falsche pos (" + pos + ") bei remove(pos)!!");
	}

	public int removeBungalows(double preis) {
		int anz = 0;
		Iterator<Bungalow> it = bungalows.iterator();
		while (it.hasNext()) {
			if (it.next().getGrundpreis() < preis) {
				it.remove();
				anz++;
			}
		}
		return anz;
	}

	public void sortBungalowsNummer() {
		// Collections.sort(bungalows, new Bungalow.NummernComparator());
		Collections.sort(bungalows);
	}

	public void sortBungalowsTagesertrag() {
		Collections.sort(bungalows, new Bungalow.ErtragsComparator());
		Collections.reverse(bungalows);
	}

	public void sortBungalowsPreis() {
		Collections.sort(bungalows, new Bungalow.PreisComparator());
	}

	// ------------------------------------- Dateien ------------------------------
	public void saveBungalows(String filename) throws ResortException {
		if (filename != null) {
			try (FileOutputStream fos = new FileOutputStream(filename);
					ObjectOutputStream oos = new ObjectOutputStream(fos);) {
				oos.writeObject(bungalows);
			} catch (FileNotFoundException e) {
				throw new ResortException(
						"Datei-Fehler bei BungalowResort.saveBungalows(" + filename + ") !\n" + e.getMessage());
			} catch (IOException e) {
				throw new ResortException("Eingabe-/Ausgabe-Fehler bei BungalowResort.saveBungalows(" + filename
						+ ") !\n" + e.getMessage());
			}
		} else
			throw new ResortException("Null-Referenz fuer BungalowResort.saveBungalows");
	}

	public void loadBungalows(String filename) throws ResortException {
		if (filename != null) {
			List<?> tmpBungalows;
			try (FileInputStream fis = new FileInputStream(filename);
					ObjectInputStream ois = new ObjectInputStream(fis);) {
				// bungalows.addAll( (LinkedList<Bungalow>) ois.readObject() ); // mit Warning

				tmpBungalows = (List<?>) ois.readObject();
				for (Object o : tmpBungalows) {
					if (o instanceof Bungalow) {
						addBungalow((Bungalow) o);
					}
				}
			} catch (FileNotFoundException e) {
				throw new ResortException(
						"Datei-Fehler bei BungalowResort.loadBungalows(" + filename + ") !\n" + e.getMessage());
			} catch (IOException e) {
				throw new ResortException("Eingabe-/Ausgabe-Fehler bei BungalowResort.loadBungalows(" + filename
						+ ") !\n" + e.getMessage());
			} catch (ClassNotFoundException e) {
				throw new ResortException(
						"Klassen-Fehler bei BungalowResort.loadBungalows(" + filename + ") !\n" + e.getMessage());
			}
		} else
			throw new ResortException("Null-Referenz fuer BungalowResort.loadBungalows");
	}

	public void exportWohnBungalows(String filename) throws ResortException {
		if (filename != null) {
			try (BufferedWriter bw = new BufferedWriter(new FileWriter(filename));) {
				bw.write(toStringCsv());
			} catch (FileNotFoundException e) {
				throw new ResortException(
						"Datei-Fehler bei BungalowResort.exportWohnBungalows(" + filename + ") !\n" + e.getMessage());
			} catch (IOException e) {
				throw new ResortException("Eingabe-/Ausgabe-Fehler bei BungalowResort.exportWohnBungalows(" + filename
						+ ") !\n" + e.getMessage());
			}
		} else
			throw new ResortException("Null-Referenz fuer BungalowResort.exportWohnBungalows");

	}

	public void importWohnBungalows(String filename) throws ResortException {
		if (filename != null) {
			try (BufferedReader br = new BufferedReader(new FileReader(filename));) {
				String zeile = br.readLine();
				String[] zeilenTeile;
				while (zeile != null) {
					zeilenTeile = zeile.trim().split(";");
					addBungalow(new WohnBungalow(zeilenTeile));
					zeile = br.readLine();
				}
			} catch (FileNotFoundException e) {
				throw new ResortException(
						"Datei-Fehler bei BungalowResort.importWohnBungalows(" + filename + ") !\n" + e.getMessage());
			} catch (IOException e) {
				throw new ResortException("Eingabe-/Ausgabe-Fehler bei BungalowResort.importWohnBungalows(" + filename
						+ ") !\n" + e.getMessage());
			}
		} else
			throw new ResortException("Null-Referenz fuer BungalowResort.importWohnBungalows");
	}

	// ------------------------------- toString --------------------------
	public String toString() {
		StringBuilder sb = new StringBuilder(1000);
		sb.append("Bungalow-Resort ").append(name).append(" -> derzeit ").append(bungalows.size())
				.append(" Bungalows\n");
		Iterator<Bungalow> it = bungalows.iterator();
		while (it.hasNext()) {
			sb.append(it.next()).append('\n');
		}
		return sb.toString();
	}

	public String toStringCsv() {
		StringBuilder sb = new StringBuilder(1000);
		Iterator<Bungalow> it = bungalows.iterator();
		while (it.hasNext()) {
			Bungalow b = it.next();
			if (b instanceof WohnBungalow) {
				sb.append(((WohnBungalow) b).toStringCsv()).append('\n');
			}
		}
		return sb.toString();
	}
}
