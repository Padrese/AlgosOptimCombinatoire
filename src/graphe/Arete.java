package graphe;

public class Arete {
	private Sommet u; // Les deux sommets incidents  à l'arête
	private Sommet v;
	private int cout;
	private int cst_f;
	private int cst_g;
	private int cst_x;
	
	/**
	 * Constructeur de la classe Arete, en spécifiant seulement le coût.
	 * @param u
	 * @param v
	 * @param cout
	 * @throws NullPointerException
	 * @throws IllegalArgumentException
	 */
	public Arete(Sommet u, Sommet v, int cout) throws NullPointerException{
		if (u == null || v == null) {
			throw new NullPointerException("L'un des sommets incidents est null!");
		}
		this.u = u;
		this.v = v;
		this.cout = cout;
		this.cst_f = 0;
		this.cst_g = 0;
		this.cst_x = 0;
	}
	
	/**
	 * Constructeur de la classe Arete, sans spécifier le coût ou les contraintes.
	 * Par défaut, celui-ci vaudra 0.
	 * @param u
	 * @param v
	 * @throws NullPointerException
	 * @throws IllegalArgumentException
	 */
	public Arete(Sommet u, Sommet v) throws NullPointerException{
		if (u == null || v == null) {
			throw new NullPointerException("L'un des sommets incidents est null!");
		}
		this.u = u;
		this.v = v;
		this.cout = 0;
		this.cst_f = 0;
		this.cst_g = 0;
		this.cst_x = 0;
	}
	
	
	/**
	 * Constructeur de la classe Arete, en spécifiant les contraintes de flot.
	 * @param u
	 * @param v
	 * @param cst_g
	 * @param cst_f
	 * @throws NullPointerException
	 * @throws IllegalArgumentException
	 */
	public Arete(Sommet u, Sommet v, int cst_f, int cst_g) throws NullPointerException, IllegalArgumentException{
		if (u == null || v == null) {
			throw new NullPointerException("L'un des sommets incidents est null!");
		}
		else if (cst_f > cst_g) {
			throw new IllegalArgumentException("La contrainte f est plus grande que la contrainte g!");
		}
		else if (cst_f > 0 && cst_g < 0) {
			throw new IllegalArgumentException("AU départ, on doit avoir f(e) <= x(e) = 0 <= g(e)!");
		}
		this.u = u;
		this.v = v;
		this.cout = 0;
		this.cst_f = cst_f;
		this.cst_g = cst_g;
		this.cst_x = 0;
	}
	
	
	/**
	 * Constructeur de la classe Arete, en spécifiant les contraintes et le coût (sert pour les m-flots par exemple)
	 * @param u
	 * @param v
	 * @param cout
	 * @param cst_g
	 * @param cst_f
	 * @throws NullPointerException
	 * @throws IllegalArgumentException
	 */
	public Arete(Sommet u, Sommet v, int cout, int cst_f, int cst_g) throws NullPointerException, IllegalArgumentException{
		if (u == null || v == null) {
			throw new NullPointerException("L'un des sommets incidents est null!");
		}
		else if (cst_f > cst_g) {
			throw new IllegalArgumentException("La contrainte f est plus grande que la contrainte g!");
		}
		else if (cst_f > 0 || cst_g < 0) {
			throw new IllegalArgumentException("AU départ, on doit avoir f(e) <= x(e) = 0 <= g(e)!");
		}
		this.u = u;
		this.v = v;
		this.cout = cout;
		this.cst_f = cst_f;
		this.cst_g = cst_g;
		this.cst_x = 0;
	}
	
	/**
	 * Constructeur de la classe Arete, où l'on précise tout.
	 * @param u
	 * @param v
	 * @param cout
	 * @param cst_x
	 * @param cst_f
	 * @param cst_g
	 * @throws NullPointerException
	 * @throws IllegalArgumentException
	 */
	public Arete(Sommet u, Sommet v, int cout, int cst_x, int cst_f, int cst_g) throws NullPointerException, IllegalArgumentException{
		if (u == null || v == null) {
			throw new NullPointerException("L'un des sommets incidents est null!");
		}
		else if (cst_f > cst_g) {
			throw new IllegalArgumentException("La contrainte f est plus grande que la contrainte g!");
		}
		else if (cst_f > cst_x || cst_g < cst_x) {
			throw new IllegalArgumentException("AU départ, on doit avoir f(e) <= x(e) <= g(e)!");
		}
		this.u = u;
		this.v = v;
		this.cout = cout;
		this.cst_f = cst_f;
		this.cst_g = cst_g;
		this.cst_x = cst_x;
	}	
	
	/**
	 * Accesseur du sommet incident u.
	 * @return
	 */
	public Sommet getU() {
		return this.u;
	}
	
	/**
	 * Accesseur du sommet incident v.
	 * @return
	 */
	public Sommet getV() {
		return this.v;
	}
	
	/**
	 * Accesseur du coût de l'arête.
	 * @return
	 */
	public int getCout() {
		return this.cout;
	}
	
	/**
	 * Accesseur de la contrainte de flot f(e).
	 * @return
	 */
	public int getCstf() {
		return this.cst_f;
	}
	
	/**
	 * Accesseur de la contrainte de flot g(e).
	 * @return
	 */
	public int getCstg() {
		return this.cst_g;
	}
	
	/**
	 * Accesseur de la contrainte de flot x(e).
	 * @return
	 */
	public int getCstx() {
		return this.cst_x;
	}
	
	public void setCstx(int cst_x) throws IllegalArgumentException{
		if (cst_f > cst_x || cst_g < cst_x) {
			throw new IllegalArgumentException("Le flot ne sera pas réalisable avec cette valeur en paramètre!");
		}
		this.cst_x = cst_x;
	}
	
	/**
	 * Représentation textuelle d'une arête.
	 */
	@Override
	public String toString() {
		String s = new String();
		s += "Arête " + this.u.getId() + this.v.getId() + " de coût " + this.cout + " de contraintes f(e): "
				+ this.cst_f + " et g(e): " + this.cst_g + " et de coût de flot x(e): " + this.cst_x;
		return s;
	}
	
	/**
	 * Redéfinition de equals au sens de deux arêtes.
	 */
	@Override
	public boolean equals(Object o) {
		if(o instanceof Arete) {
			Arete a = (Arete) o;
			if ((this.u == a.getU() && this.v == a.getV())) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Redéfinition de hashCode.
	 */
	@Override
	public int hashCode() {
		String concat = this.u.getId() + this.v.getId();
		return concat.hashCode();
	}
	

}
