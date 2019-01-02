package de.hdm.softwarePraktikumGruppe1.server;

import java.sql.Timestamp;
import java.util.Vector;
import java.util.Vector;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import de.hdm.softwarePraktikumGruppe1.client.service.Pinnwand.PinnwandService;
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
public class PinnwandverwaltungImpl extends RemoteServiceServlet implements Pinnwandverwaltung{

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
	public Vector<User> showAllUser(){
		return uMapper.findAll();
	}

	// TESTMETHODE
	
	/*
	 * VORSICHT TESTMETHODE
	 * 
	 * Bitte beim anfangen der richtigen implementierung entweder löschen oder in der korrekten methode
	 * den methodenkörper wiederverwenden!!!
	 */
	public User createSingleUserTestMethod(String vorname, String nachname, String nickname) {
		
		//Erstellen eines Nutzerobjekts mit Vorname, Nachname und Nachname
		User u = new User();
		
		u.setFirstName(vorname);
		u.setLastName(nachname);
		u.setNickname(nickname);
		
		//Speichern in der DB
		return this.uMapper.insert(u);
			
	}
	
	/**
	 * Methode um einen User zu erstellen.
	 */
	public void createUser(String firstName, String lastName, String nickName, String gMail, Timestamp timestamp ) throws IllegalArgumentException {
		User u = new User();
		u.setFirstName(firstName);
		u.setLastName(lastName);
		u.setNickname(nickName);
		u.setGMail(gMail);
		u.setCreationTimeStamp(timestamp);
		this.uMapper.insert(u);
	
	}
	
	/**
	 * Methode um einen User zu speichern
	 */
	public void editUser(User u) {
		uMapper.update(u);
	}
	
	/**
	 * Methode um einen User zu Loeschen
	 */
	public void deleteUser(User u) {
		//Alle Likes des Users löschen
		Vector<Like> likesOfUser = this.lMapper.getLikesOfUser(u.getUserId());
		if (likesOfUser!=null) {
			for(Like l : likesOfUser) {
				this.lMapper.deleteLike(l);
			}
		}
		//Alle Abonements des Users löschen
		Vector<Abonnement> abonnementsOfUser = this.aMapper.getAbonnementsOfUser(u.getUserId());
		if (abonnementsOfUser!=null) {
			for(Abonnement a : abonnementsOfUser) {
				this.aMapper.deleteAbonnement(a);
			}
		}
		
		//Alle Abos der Pinnwand des Users löschen
		Vector<Abonnement> abonnementsOfPinnwand = this.aMapper.getAbonnementsOfPinnwand(this.pMapper.findPinnwandByUser(u.getUserId()).getPinnwandId());
		if (abonnementsOfPinnwand!=null) {
			for(Abonnement a : abonnementsOfPinnwand) {
				this.aMapper.deleteAbonnement(a);
			}
		}
		//Alle Kommentare des Users löschen
		Vector<Kommentar> kommentareOfUser = this.kMapper.getKommentareOfUser(u.getUserId());
		if (kommentareOfUser!=null) {
			for(Kommentar k : kommentareOfUser) {
				this.kMapper.deleteKommentar(k);
			}
		}
		
		//Alle Beiträge des Users löschen
		Vector<Beitrag> beitraegeOfUser = bMapper.getBeitraegeOfPinnwand(u.getUserId());
		if (beitraegeOfUser!=null) {
			for (Beitrag b : beitraegeOfUser) {
				deleteBeitrag(b);
			}
		}
		//Pinnwand des Users löschen
		pMapper.deletePinnwandOfUser(u);
		//User löschen
		uMapper.deleteUser(u.getUserId());
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
	public User searchUserById(int userId) {
		return uMapper.findByUserID(userId);
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
	public User searchUserByNickname(String nickname) {
		return uMapper.findUserByNickname(nickname);
	}
	
	/**
	 * Methode um einen Beitrag zu erzeugen
	 */
	public void createBeitrag(String text, int userId, Timestamp timeStamp) {
		Beitrag b = new Beitrag();
		b.setText(text);
		b.setOwnerId(userId);
		b.setCreationTimeStamp(timeStamp);
		bMapper.insertBeitrag(b);
	}
	
	/**
	 * Methode um alle Beiträge eines Users auszugeben
	 */
	public Vector<Beitrag> findAllBeitraegeOfUser(User u){
		return bMapper.getAllBeitraege();
	}
	
	/**
	 * Methode um einen Beitrag zu Loeschen
	 */
	public void deleteBeitrag(Beitrag b) {
		//Alle Likes löschen
		Vector<Like> likesOfBeitrag = this.lMapper.getLikesOfBeitrag(b.getBeitragId());
		if (likesOfBeitrag != null) {
			for (Like l : likesOfBeitrag) {
				lMapper.deleteLike(l);
			}
		}
		//Alle Kommentare löschen
		Vector<Kommentar> kommentareOfBeitrag = this.kMapper.getKommentareOfBeitrag(b.getBeitragId());
		if (kommentareOfBeitrag != null) {
			for (Kommentar k : kommentareOfBeitrag) {
				kMapper.deleteKommentar(k);
			}
		}
		//Beitrag löschen
		bMapper.deleteBeitrag(b);
	}
	
	/**
	 * Methode um einen Beitrag zu Bearbeiten
	 */
	public Beitrag editBeitrag(Beitrag b) {
		return bMapper.updateBeitrag(b);
	}
	
	/**
	 * Methode um alle Abonnements eines Users anzuzeigen
	 */
	public Vector<Abonnement> showAllAbonnementsByUser(int userId){
		return aMapper.getAbonnementsOfUser(userId);
	}
	
	/**
	 * Methode um ein neues Abonnement zu erzeugen
	 */
	public void createAbonnement(int userId, int pinnwandId) {
		Abonnement a = new Abonnement();
		a.setOwnerId(userId);
		a.setPinnwandId(pinnwandId);
		
		aMapper.insert(a);
		
	}
	
	/**
	 * Methode um ein bestehendes Abonnement zu Loeschen
	 */
	public void deleteAbonnement(Abonnement a) {
		aMapper.deleteAbonnement(a);
	}
	
	/**
	 * Methode um einen neues Kommentar zu erzeugen
	 */
	public void createKommentar(String text, int userId, int beitragId, Timestamp timeStamp) {
		Kommentar k = new Kommentar();
		
		k.setText(text);
		k.setOwnerId(userId);
		k.setBeitragId(beitragId);
		k.setCreationTimeStamp(timeStamp);
		
		kMapper.insertKommentar(k);
	}
	
	/**
	 * Methode zum Loeschen eines Kommentars
	 */
	public void deleteKommentar(Kommentar k) {
		kMapper.deleteKommentar(k);
	}
	
	/**
	 * Methode zum anzeigen aller Kommentare
	 */
	public Vector<Kommentar> findAllKommentareOfBeitrag(int beitragId){
		return kMapper.getKommentareOfBeitrag(beitragId);
		
	}
	
	/**
	 * Methode zum Bearbeiten eines Kommentars
	 */
	public void editKommentar(Kommentar k) {
		 kMapper.updateKommentar(k);
	}
	
	/**
	 * Methode zum erzeugen eines Likes
	 */
	public void createLike(User u, Beitrag b) {
		Like l1 = new Like();
		l1.setOwner(u);
		l1.setBeitrag(b);
		lMapper.insertLike(l1);
		
	}
	
	/**
	 * Methode zur Ueberpruefung ob der Beitrag bereits geliket ist
	 */
	public boolean likeCheck(User u, Beitrag b) {
		return lMapper.likeCheck(u, b);	
	}
	
	/**
	 * Methode um einen Beitrag zu entliken
	 */
	public void deleteLike(Like l) {
		lMapper.deleteLike(l);
		
	}
	
//	/**
//	 * Methode um ein Like zu suchen (???)
//	 */
//	public Like searchLike(Like l) {
//		// Nutzen?
//		// Worin besteht der Unterschied zur Methode likeCheck?
//		return null;
//	}
	
	/**
	 * Methode um alle Likes eines Beitrags zu zaehlen
	 */
	public int countLikes(Beitrag b) {
		return lMapper.getLikesOfBeitrag(b.getBeitragId()).size();
	}
	
	/**
	 * Methode um Likes eines Beitrags zu entfernen
	 */
	public void deleteLikesOfBeitrag(Beitrag b) {
		lMapper.deleteAllLikesFromBeitrag(b);
	}
	
	/*
	 * Methode um eine Pinnwand zu erstellen
	 */
	
	public void createPinnwand(User u, Timestamp timestamp) {
		if (pMapper.findPinnwandByUser(u)==null) {
			Pinnwand p = new Pinnwand();
			p.setId(1);
			p.setOwner(u.getUserId());
			p.setCreationTimeStamp(timestamp);
			pMapper.insertPinnwand(p);
		}
	}
	
	/*
	 * Methode um die Pinnwand eines Users zu löschen
	 */
	
	public void deletePinnwand(Pinnwand p) {
		
		Vector <Beitrag> beitraegeOfPinnwand = this.bMapper.getBeitraegeOfPinnwand(p.getPinnwandId());
		Vector <Abonnement> abonnementsOfPinnwand = this.aMapper.getAbonnementsOfPinnwand(p.getPinnwandId());
		if(beitraegeOfPinnwand!=null) {
			for (Beitrag b : beitraegeOfPinnwand) {
			Vector <Kommentar> kommentare =  this.kMapper.getKommentareOfBeitrag(b.getBeitragId());
			Vector <Like> likes = this.lMapper.getLikesOfBeitrag(b.getBeitragId());
			
				if(kommentare!=null) {
					for (Kommentar k : kommentare) {
						kMapper.deleteKommentar(k);
					}
				}
				if(likes!=null) {
					for(Like l : likes) {
						lMapper.deleteLike(l);
					}
				}
				bMapper.deleteBeitrag(b);
			}
		}
		if(abonnementsOfPinnwand!=null) {
			for (Abonnement a: abonnementsOfPinnwand) {
				aMapper.deleteAbonnement(a);
			}
		}
		pMapper.deletePinnwand(p);
		
	}


}
