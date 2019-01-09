package de.hdm.softwarePraktikumGruppe1.server;

import java.sql.Timestamp;
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
	

	/* Initialisierungsmethode, welche alle Mapper initialisiert.
	 * 
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
	 * 
	 * Methode die alle User als Vector zurueck gibt
	 */
//	public Vector<User> showAllUser(){
//		return uMapper.findAll();
//	}

	// TESTMETHODE
	
	/*
	 * VORSICHT TESTMETHODE
	 * 
	 * Bitte beim anfangen der richtigen implementierung entweder löschen oder in der korrekten methode
	 * den methodenkörper wiederverwenden!!!
	 */
//	public User createSingleUserTestMethod(String vorname, String nachname, String nickname) {
//		
//		//Erstellen eines Nutzerobjekts mit Vorname, Nachname und Nachname
//		User u = new User();
//		
//		u.setFirstName(vorname);
//		u.setLastName(nachname);
//		u.setNickname(nickname);
//		
//		//Speichern in der DB
//		return this.uMapper.insert(u);
//			
//	}
	
	/**
	 * Methode um einen User zu erstellen.
	 */
	public User createUser(String firstName, String lastName, String nickName, String gMail, Timestamp timestamp ) throws IllegalArgumentException {
		User u = new User();
		u.setFirstName(firstName);
		u.setLastName(lastName);
		u.setNickname(nickName);
		u.setGMail(gMail);
		u.setCreationTimeStamp(timestamp);
		this.uMapper.insert(u);
		return u;
	
	}
	
	/**
	 * Methode um einen User zu speichern
	 */
	public void editUser(User u) {
		this.uMapper.update(u);
	}
	
	/**
	 * Methode um einen User zu Loeschen
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
		
//		//Alle Abos der Pinnwand des Users löschen
//		Vector<Abonnement> abonnementsOfPinnwand = this.aMapper.getAbonnementsOfPinnwand(this.pMapper.findPinnwandByUser(u.getUserId()).getPinnwandId());
//		if (abonnementsOfPinnwand!=null) {
//			for(Abonnement a : abonnementsOfPinnwand) {
//				this.aMapper.deleteAbonnement(a);
//			}
//		}
		//Alle Kommentare des Users löschen
		Vector<Kommentar> kommentareOfUser = this.kMapper.findKommentareOfUser(u.getUserId());
		if (kommentareOfUser!=null) {
			for(Kommentar k : kommentareOfUser) {
				this.kMapper.deleteKommentar(k);
			}
		}
		
//		//Alle Beiträge des Users löschen
//		Vector<Beitrag> beitraegeOfUser = bMapper.getBeitraegeOfPinnwand(u.getUserId());
//		if (beitraegeOfUser!=null) {
//			for (Beitrag b : beitraegeOfUser) {
//				deleteBeitrag(b);
//			}
//		}
		//Delete Pinnwand
		deletePinnwand(this.pMapper.findPinnwandByUserId(u.getUserId()));
		
		//User löschen
		this.uMapper.deleteUser(u);
	}
	
	/**
	 * Methode zur Ueberpruefung der Zugangsberechtigung 
	 */
	public User loginCheck(String nickname, String password) {
		return null;
	}
	
	/**
	 * Methode um einen User anhand seiner ID zu suchen
	 */
	public User getUserById(int userId) {
		return this.uMapper.findUserById(userId);
	}
	
//	/**
//	 * Methode um einen User upzudaten (???)
//	 */
//	public User updateUser(User u) {
//		
//		return null;
//	}
	
	/**
	 * Methode um einen User anhand seines Nicknamens zu suchen
	 */
	public Vector<User> getUserByNickname(String nickname) {
		return uMapper.findUserByNickname(nickname);
	}
	
	/**
	 * Methode um einen User anhand seines Vornamens zu suchen
	 */
	public Vector<User> getUserByFirstName(String fName) {
		return uMapper.findUserByFirstName(fName);
	}
	
	/**
	 * Methode um einen User anhand seines Nachnamens zu suchen
	 */
	public Vector<User> getUserByLastName(String lName) {
		return uMapper.findUserByLastName(lName);
	}
	
	/**
	 * Methode um einen User anhand seiner Gmail zu suchen
	 */
	public User getUserByGmail(String gMail) {
		return uMapper.findUserByGmail(gMail);
	}
	
	/**
	 * Methode um einen Beitrag zu erzeugen
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
	
	public Beitrag getBeitragById(int beitragId) {
		return this.bMapper.findBeitragById(beitragId);
	}
	
	/**
	 * Methode um alle Beiträge eines Users auszugeben
	 */
	public Vector<Beitrag> getAllBeitraegeOfUser(User u){
		return this.bMapper.findBeitraegeOfUser(u.getUserId());
	}
	
	/*
	 * Methode die die Anzahl der Beitraege zurück gibt
	 */
	
	public int getBeitragAmountOfUser(User u) {
		int i = bMapper.findBeitraegeOfUser(u.getUserId()).size();
		return i;
	}
	
	/**
	 * Methode um einen Beitrag zu Loeschen
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
	 */
	public Beitrag editBeitrag(Beitrag b) {
		return this.bMapper.updateBeitrag(b);
	}
	
	/**
	 * Methode um alle Abonnements eines Users anzuzeigen
	 */
	public Vector<Abonnement> showAllAbonnementsByUser(User u){
		return this.aMapper.findAbonnementsOfUser(u.getUserId());
	}
	
	/**
	 * Methode um ein neues Abonnement zu erzeugen
	 */
	public Abonnement createAbonnement(User u, Pinnwand p, Timestamp timeStamp) {
		Abonnement a = new Abonnement();
		a.setOwnerId(u.getUserId());
		a.setPinnwandId(p.getPinnwandId());
		
		this.aMapper.insert(a);
		return a;
		
	}
	
	/**
	 * Methode um ein bestehendes Abonnement zu Loeschen
	 */
	public void deleteAbonnement(Abonnement a) {
		this.aMapper.deleteAbonnement(a);
	}
	
	public Kommentar getKommentarById(int kommentarId) {
		return this.kMapper.findKommentarById(kommentarId);
	}
	/**
	 * Methode um einen neues Kommentar zu erzeugen
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
	 */
	public void deleteKommentar(Kommentar k) {
		this.kMapper.deleteKommentar(k);
	}
	
	/**
	 * Methode zum anzeigen aller Kommentare
	 */
	public Vector<Kommentar> getAllKommentareOfBeitrag(Beitrag b){
		return this.kMapper.findKommentareOfBeitrag(b.getBeitragId());
		
	}
	
	/**
	 * Methode zum Bearbeiten eines Kommentars
	 */
	public void editKommentar(Kommentar k) {
		 this.kMapper.updateKommentar(k);
	}
	
	public Like getLikeById(int likeId) {
		return this.lMapper.findLikeById(likeId);
	}
	
	/**
	 * Methode zum erzeugen eines Likes
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
	 */
	public void deleteLike(Like l) {
		this.lMapper.deleteLike(l);
		
	}
	
	/**
	 * Methode um alle Likes eines Beitrags zu zaehlen
	 */
	public int countLikes(Beitrag b) {
		Vector <Like> likes = this.lMapper.findLikesOfBeitrag(b.getBeitragId());
		
		return likes.size();
	}
	
	/**
	 * Methode um Likes eines Beitrags zu entfernen
	 */
	public void deleteLikesOfBeitrag(Beitrag b) {
		Vector<Like> likesOfBeitrag = this.lMapper.findLikesOfBeitrag(b.getBeitragId());
		if(likesOfBeitrag!=null) {
			for(Like l : likesOfBeitrag) {
				this.lMapper.deleteLike(l);
			}
		}
	}
	
	/*
	 * Methode um eine Pinnwand zu erstellen
	 */
	
	public Pinnwand getPinnwandById(int pinnwandId) {
		return this.pMapper.findPinnwandById(pinnwandId);
	}
	
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
	 * @param User
	 */
	
	public Pinnwand getPinnwandByUserId(int userId) {
		if(this.pMapper.findPinnwandByUserId(userId) == null) {
			return null;
		}else {
			Pinnwand p = this.pMapper.findPinnwandByUserId(userId);
			return p;
		}
	}
	
	/*
	 * Methode um die Pinnwand eines Users zu löschen
	 */
	
	public void deletePinnwand(Pinnwand p) {
		
		Vector <Beitrag> beitraegeOfPinnwand = this.bMapper.findBeitraegeOfPinnwand(p.getPinnwandId());
		Vector <Abonnement> abonnementsOfPinnwand = this.aMapper.findAbonnementsOfPinnwand(p.getPinnwandId());
		if(beitraegeOfPinnwand!=null) {
			for (Beitrag b : beitraegeOfPinnwand) {
//			Vector <Kommentar> kommentare =  this.kMapper.getKommentareOfBeitrag(b.getBeitragId());
//			Vector <Like> likes = this.lMapper.getLikesOfBeitrag(b.getBeitragId());
//			
//				if(kommentare!=null) {
//					for (Kommentar k : kommentare) {
//						kMapper.deleteKommentar(k);
//					}
//				}
//				if(likes!=null) {
//					for(Like l : likes) {
//						lMapper.deleteLike(l);
//					}
//				}
//				bMapper.deleteBeitrag(b);
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



	

}
