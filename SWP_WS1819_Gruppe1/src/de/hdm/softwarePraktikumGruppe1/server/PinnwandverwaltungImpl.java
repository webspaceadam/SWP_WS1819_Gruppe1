package de.hdm.softwarePraktikumGruppe1.server;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Vector;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import de.hdm.softwarePraktikumGruppe1.server.db.AbonnementMapper;
import de.hdm.softwarePraktikumGruppe1.server.db.BeitragMapper;
import de.hdm.softwarePraktikumGruppe1.server.db.KommentarMapper;
import de.hdm.softwarePraktikumGruppe1.server.db.LikeMapper;
import de.hdm.softwarePraktikumGruppe1.server.db.PinnwandMapper;
import de.hdm.softwarePraktikumGruppe1.server.db.UserMapper;
import de.hdm.softwarePraktikumGruppe1.shared.Pinnwandverwaltung;
import de.hdm.softwarePraktikumGruppe1.shared.bo.Abonnement;
import de.hdm.softwarePraktikumGruppe1.shared.bo.Beitrag;
import de.hdm.softwarePraktikumGruppe1.shared.bo.Kommentar;
import de.hdm.softwarePraktikumGruppe1.shared.bo.Like;
import de.hdm.softwarePraktikumGruppe1.shared.bo.Pinnwand;
import de.hdm.softwarePraktikumGruppe1.shared.bo.User;

/**
 * @author SebastianHermann
 * @author Yesin Soufi
 * Klasse die das Interface Pinnwandverwaltung Implementiert und das RemoteServiceServlet als Superklasse besitzt
 */
@SuppressWarnings("serial")
public class PinnwandverwaltungImpl extends RemoteServiceServlet implements Pinnwandverwaltung{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private UserMapper uMapper = null;
	private PinnwandMapper pMapper = null;
	private BeitragMapper bMapper = null;
	private KommentarMapper kMapper = null;
	private LikeMapper lMapper = null;
	private AbonnementMapper aMapper = null;

	/**
	 * Konstruktor der Klasse PinnwandverwaltungIMpl der bei jedem erzeugten Objekt dieser Klasse ausfgerufen wird
	 * @return 
	 */
	public PinnwandverwaltungImpl() throws IllegalArgumentException {
		
	}
	

	/** Initialisierungsmethode, welche alle Mapper initialisiert.
	 * @throws IllegalArgumentException
	 */
	
	public void init() throws IllegalArgumentException {
		this.uMapper = UserMapper.userMapper();
		this.pMapper = PinnwandMapper.pinnwandMapper();
		this.bMapper = BeitragMapper.beitragMapper();
		this.kMapper = KommentarMapper.kommentarMapper();
		this.lMapper = LikeMapper.likeMapper();
		this.aMapper = AbonnementMapper.abonnementMapper();
	}

	
	/**
	 * Methode um einen User zu erstellen.
	 * @param firstName
	 * @param lastName
	 * @param nickName
	 * @param gMail
	 * @param timestamp
	 * @throws IllegalArgumentException
	 * @return fertiges User Objekt
	 */
	
	public User createUser(String firstName, String lastName, String nickName, String gMail, Timestamp timestamp) throws IllegalArgumentException {
		User u = new User();
		u.setFirstName(firstName);
		u.setLastName(lastName);
		u.setNickname(nickName);
		u.setGMail(gMail);
		u.setCreationTimeStamp(timestamp);
		this.createPinnwand(u, timestamp);
		
		this.uMapper.insert(u);
		return u;
	
	}
	
	/**
	 * Methode um einen User zu speichern
	 * @param u der User
	 */

	public void editUser(User u) {
		this.uMapper.update(u);
	}
	
	/**
	 * Methode um einen User zu Loeschen
	 * @param u der User
	 */
	
	public void deleteUser(User u) {
		
		//Delete AbonnementsTo
		
		//Alle Likes des Users löschen
		Vector<Like> likesOfUser = this.lMapper.findLikesOfUser(u.getUserId());
		if (likesOfUser!=null) {
			for(Like l : likesOfUser) {
				this.lMapper.deleteLike(l);
			}
		}
		//Alle Abonements des Users löschen
		Vector<Abonnement> abonnementsOfUser = this.aMapper.findAbonnementsOfUser(u.getUserId());
		if (abonnementsOfUser!=null) {
			for(Abonnement a : abonnementsOfUser) {
				this.aMapper.deleteAbonnement(a);
			}
		}
		

		//Alle Kommentare des Users löschen
		Vector<Kommentar> kommentareOfUser = this.kMapper.findKommentareOfUser(u.getUserId());
		if (kommentareOfUser!=null) {
			for(Kommentar k : kommentareOfUser) {
				this.kMapper.deleteKommentar(k);
			}
		}
		

		this.deletePinnwand(this.pMapper.findPinnwandByUserId(u.getUserId()));
		
		//User löschen
		this.uMapper.deleteUser(u);
	}
	
	/**
	 * Methode zur Ueberpruefung der Zugangsberechtigung 
	 * @param nickname
	 * @param password
	 * @return null
	 */
	
	public User loginCheck(String nickname, String password) {
		return null;
	}
	
	/**
	 * Methode um einen User anhand seiner ID zu suchen
	 * @param userID
	 * @return userID der uMapper
	 */
	
	public User getUserById(int userId) {
		return this.uMapper.findUserById(userId);
	}
	

	/**
	 * Methode um einen User anhand seines Nicknamens zu suchen
	 * @param nickname
	 * @return nickname des uMapper
	 */
	
	public Vector<User> getUserByNickname(String nickname) {
		return uMapper.findUserByNickname(nickname);
	}
	
	/**
	 * Methode um einen User anhand seines Vornamens zu suchen
	 * @param fname
	 * @return firstName des uMapper
	 */

	public Vector<User> getUserByFirstName(String fName) {
		return uMapper.findUserByFirstName(fName);
	}
	
	/**
	 * Methode um einen User anhand seines Nachnamens zu suchen
	 * @param lName
	 * @return lastName des uMapper
	 */
	
	public Vector<User> getUserByLastName(String lName) {
		return uMapper.findUserByLastName(lName);
	}
	
	/**
	 * Methode um einen User anhand seiner Gmail zu suchen
	 * @param gMail
	 * @return gMail
	 */
	
	public User getUserByGmail(String gMail) {
		return uMapper.findUserByGmail(gMail);
	}
	
	/**
	 * Methode um einen Beitrag zu erzeugen
	 * @param text 
	 * @param u
	 * @timeStamp
	 * @return b der erstellte Beitrag
	 */
	
	public Beitrag createBeitrag(String text, User u, Timestamp timeStamp) {
		Beitrag b = new Beitrag();
		b.setInhalt(text);
		b.setOwnerId(u.getUserId());
		b.setPinnwandId(this.pMapper.findPinnwandByUserId(u.getUserId()).getPinnwandId());
		b.setCreationTimeStamp(timeStamp);
		this.bMapper.insertBeitrag(b);
		return b;
	}
	
	/**
	 * 
	 * @param beitragId
	 * @return beitragId des bMapper
	 */
	
	public Beitrag getBeitragById(int beitragId) {
		return this.bMapper.findBeitragById(beitragId);
	}
	
	/**
	 * Methode um alle Beiträge eines Users auszugeben
	 * @param u
	 * @return findet alle Beiträge des Users nach der Id
	 */
	 
	public Vector<Beitrag> getAllBeitraegeOfUser(User u){
		return this.bMapper.findBeitraegeOfUser(u.getUserId());
	}
	
	/**
	 * Methode die die Anzahl der Beitraege zurück gibt
	 * @param u
	 *  @return i findet die Anzahl der Beiträge des Users nach der Id
	 */
	
	public int getBeitragAmountOfUser(User u) {
		int i = bMapper.findBeitraegeOfUser(u.getUserId()).size();
		return i;
	}
	
	/**
	 * Methode um einen Beitrag zu Loeschen
	 * @param b
	 */
	
	public void deleteBeitrag(Beitrag b) {
		//Alle Likes löschen
		Vector<Like> likesOfBeitrag = this.lMapper.findLikesOfBeitrag(b.getBeitragId());
		if (likesOfBeitrag != null) {
			for (Like l : likesOfBeitrag) {
				this.deleteLike(l);
				
			}
		}
		//Alle Kommentare löschen
		Vector<Kommentar> kommentareOfBeitrag = this.kMapper.findKommentareOfBeitrag(b.getBeitragId());
		if (kommentareOfBeitrag != null) {
			for (Kommentar k : kommentareOfBeitrag) {
				System.out.println(k.getKommentarId());
				this.deleteKommentar(k);	
			}
		}
		//Beitrag löschen
		bMapper.deleteBeitrag(b);
	}
	
	/**
	 * Methode um einen Beitrag zu Bearbeiten
	 * @param b
	 * @return b der editierte Beitrag des Users
	 */
	
	public Beitrag editBeitrag(Beitrag b) {
		return this.bMapper.updateBeitrag(b);
	}
	
	/**
	 * Methode um alle Abonnements eines Users anzuzeigen
	 * @param u
	 * @return u findet die Abbonements des Users nach Id
	 */
	
	public Vector<Abonnement> showAllAbonnementsByUser(User u){
		return this.aMapper.findAbonnementsOfUser(u.getUserId());
	}
	
	/**
	 * Methode um ein neues Abonnement zu erzeugen
	 * @param u
	 * @param p
	 * @param timeStamp
	 * @return a
	 */
	
	public Abonnement createAbonnement(User u1, User u2, Timestamp timestamp) {
		Abonnement a = new Abonnement();
		a.setOwnerId(u1.getUserId());
		a.setPinnwandId((this.pMapper.findPinnwandByUserId(u2.getUserId())).getPinnwandId());
		this.aMapper.insert(a);
		return a;
		
	}
	
	/**
	 * Methode um ein bestehendes Abonnement zu Loeschen
	 * @param a
	 * @return a löscht das Abbonnement
	 */
	
	public void deleteAbonnement(Abonnement a) {
		this.aMapper.deleteAbonnement(a);
	}
	public boolean abonnementCheck(User u, Pinnwand p) {
		if(this.aMapper.findAbonnementsOfPinnwandAndUser(u.getUserId(), p.getPinnwandId())!=null) {
			return true;
		}else {
			return false;
		}
	}
	
	public Abonnement getAbonnementBetweenUsers(User u1, User u2) {
		return this.aMapper.findAbonnementsOfPinnwandAndUser(u1.getUserId(), pMapper.findPinnwandByUserId(u2.getUserId()).getPinnwandId());
	}
	
	/**
	* Methode um ein Kommentar nach der Id zu finden
	* @param kommentarId
	* @return kommentarId findet die KommentarId des kMapper
	*/
	
	public Kommentar getKommentarById(int kommentarId) {
		return this.kMapper.findKommentarById(kommentarId);
	}
	
	/**
	 * Methode um einen neuen Kommentar zu erzeugen
	 * @param inhalt
	 * @param userId
	 * @param beitragId
	 * @param timeStamp
	 * @return k
	 */
	
	public Kommentar createKommentar(String inhalt, int userId, int beitragId, Timestamp timeStamp) {
		Kommentar k = new Kommentar();
		k.setInhalt(inhalt);
		k.setOwnerId(userId);
		k.setBeitragId(beitragId);
		k.setCreationTimeStamp(timeStamp);

		this.kMapper.insertKommentar(k);
		return k;
	}
	
	/**
	 * Methode zum Loeschen eines Kommentars
	 * @param k
	 */
	
	public void deleteKommentar(Kommentar k) {
		this.kMapper.deleteKommentar(k);
	}
	
	/**
	 * Methode zum anzeigen aller Kommentare
	 * @param b
	 * @return b findet die die Kommentare des Beitrags nach der BeitragId
	 */
	
	public Vector<Kommentar> getAllKommentareOfBeitrag(Beitrag b){
		return this.kMapper.findKommentareOfBeitrag(b.getBeitragId());
		
	}
	
	/**
	 * Methode zum Bearbeiten eines Kommentars
	 * @param k
	 */
	
	public void editKommentar(Kommentar k) {
		 this.kMapper.updateKommentar(k);
	}
	
	public Like getLikeById(int likeId) {
		return this.lMapper.findLikeById(likeId);
	}
	
	/**
	 * Methode zum erzeugen eines Likes
	 * @param u
	 * @param b
	 * @param timestamp
	 * @return l  
	 * @ return null 
	 */
	
	public Like createLike(User u, Beitrag b, Timestamp timestamp) {
		if(this.likeCheck(u, b)==null) {
			Like l = new Like();
			l.setOwnerId(u.getUserId());
			l.setBeitragId(b.getBeitragId());
			this.lMapper.insertLike(l);
			return l;
		}
		return null;
	}
	
	/**
	 * Methode zur Ueberpruefung ob der Beitrag bereits geliket ist
	 * @param u
	 * @param b
	 * @return 
	 * @return null
	 */
	
	public Like likeCheck(User u, Beitrag b) {
		if (this.lMapper.findLikeOfUserAndBeitrag(u.getUserId(), b.getBeitragId())!=null) {
			return this.lMapper.findLikeOfUserAndBeitrag(u.getUserId(), b.getBeitragId());
		}else {
			return null;
		}
	}
	
	
	/**
	 * Methode um einen Beitrag zu entliken
	 * @param l
	 * @return true das bestätigt das der Beitrag entliked wurde
	 */
	
	public Boolean deleteLike(Like l) {
		this.lMapper.deleteLike(l);
		return true;
	}
	
	/**
	 * Methode um alle Likes eines Beitrags zu zaehlen
	 * @param b
	 * @return Anzahl der Likes des jeweiligen Beitrag
	 */
	
	public int countLikes(Beitrag b) {
		Vector <Like> likes = this.lMapper.findLikesOfBeitrag(b.getBeitragId());
		
		return likes.size();
	}
	
	/**
	 * Methode um Likes eines Beitrags zu entfernen
	 * @param 
	 */
	
	public void deleteLikesOfBeitrag(Beitrag b) {
		Vector<Like> likesOfBeitrag = this.lMapper.findLikesOfBeitrag(b.getBeitragId());
		if(likesOfBeitrag!=null) {
			for(Like l : likesOfBeitrag) {
				this.lMapper.deleteLike(l);
			}
		}
	}
	
	/**
	 * Methode zum finden einer Pinnwand nach der Id
	 * @param pinnwandId
	 * @return gibt die Pinnwand nach der pinnwandId zurück 
	 */
	
	public Pinnwand getPinnwandById(int pinnwandId) {
		return this.pMapper.findPinnwandById(pinnwandId);
	}
	
	/**
	 * Methode um eine Pinnwand zu erstellen
	 * @param u
	 * @param timestamp
	 * @return p erstellt eine Pinnwand
	 * @return null es existiert schon eine Pinnwand des User
	 */
	
	public Pinnwand createPinnwand(User u, Timestamp timestamp) {
		if (this.pMapper.findPinnwandByUserId(u.getUserId()) == null) {
			Pinnwand p = new Pinnwand();
			p.setPinnwandId(1);
			p.setOwnerId(u.getUserId());
			p.setCreationTimeStamp(timestamp);
			this.pMapper.insertPinnwand(p);
			return p;
		}
		return null;
	}
	
	/**
	 * Methode um eine Pinnwand auszugeben
	 * @param u
	 * @return null falls keine Pinnwand nach der UserId gefunden wurde
	 * @return p gibt die Pinnwand des jeweiligen User zurück
	 */
	
	public Pinnwand getPinnwandOfUser(User u) {
		if(this.pMapper.findPinnwandByUserId(u.getUserId()) == null) {
			return null;
		}else {
			Pinnwand p = this.pMapper.findPinnwandByUserId(u.getUserId());
			return p;
		}
	}
	
	/**
	 * Methode um die Pinnwand eines Users zu löschen
	 * @param p
	 */
	
	public void deletePinnwand(Pinnwand p) {
		
		Vector <Beitrag> beitraegeOfPinnwand = this.bMapper.findBeitraegeOfPinnwand(p.getPinnwandId());
		Vector <Abonnement> abonnementsOfPinnwand = this.aMapper.findAbonnementsOfPinnwand(p.getPinnwandId());
		if(beitraegeOfPinnwand!=null) {
			for (Beitrag b : beitraegeOfPinnwand) {
				this.deleteBeitrag(b);
			}
		}
		if(abonnementsOfPinnwand!=null) {
			for (Abonnement a: abonnementsOfPinnwand) {
				this.deleteAbonnement(a);
			}
		}
		this.pMapper.deletePinnwand(p);
		
	}
	
	/**Methode um einen User zu suchen
	 * @param searchQuery
	 * @return users gibt die User zurück die gefunden wurden.
	 */
	
	public Vector<User> searchFunction(String searchQuery){
		HashSet<User> hs = new HashSet<User>();
		Vector<User> users = new Vector<User>();
		String s = searchQuery;
		hs.addAll(this.getUserByFirstName(s));
		hs.addAll(this.getUserByLastName(s));
		hs.addAll(this.getUserByNickname(s));
		
		Iterator<User> it = hs.iterator();
	     while(it.hasNext()){
	        users.add(it.next());
	     }
		
		return users;
	}

}
