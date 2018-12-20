package de.hdm.softwarePraktikumGruppe1.client.service.ReportGenerator;

import java.util.ArrayList;
import java.util.Date;

import com.google.gwt.user.client.rpc.AsyncCallback;

import de.Notizbuch.client.gui.MainGUI;
import de.Notizbuch.client.service.NotizbuchServiceAsync;
import de.hdm.softwarePraktikumGruppe1.client.service.Pinnwand.PinnwandServiceClientImpl.DefaultCallback1;
import de.hdm.softwarePraktikumGruppe1.shared.bo.Beitrag;
import de.hdm.softwarePraktikumGruppe1.shared.bo.Kommentar;
import de.hdm.softwarePraktikumGruppe1.shared.bo.Like;
import de.hdm.softwarePraktikumGruppe1.shared.bo.Pinnwand;
import de.hdm.softwarePraktikumGruppe1.shared.bo.User;

public class ReportGeneratorServiceClientImpl implements ReportGeneratorServiceInt{
	
	private ReportGeneratorServiceAsync service;
	//private MainGUI maingui;
	
	/**
	 * Methode die die Beitraege eines Users zurück gibt
	 */
	public void getBeitraegeFromUser(User u, Date d){
		this.service.getBeitraegeFromUser(u, d, new DefaultCallback1());
	}
	
	private class DefaultCallback1 implements AsyncCallback {

		@Override
		public void onFailure(Throwable caught) {
			System.out.println("An Error has occured");
			
		}

		@Override
		public void onSuccess(Object result) {
			if(result instanceof User)	{
				User user = (User) result;
				//maingui.getBeitraegeFromUser(user);
			}else if(result instanceof Date)	{
				Date date = (Date) result;
				//maingui.getBeitraegeFromUser(date);
			}
		}
	}
	
	/**
	 * Methode die die Likes eines Users zurück gibt
	 */
	public void getLikesFromUser(User u, Date d){
		this.service.getLikesFromUser(u, d, new DefaultCallback2());
	}
	
	private class DefaultCallback2 implements AsyncCallback {

		@Override
		public void onFailure(Throwable caught) {
			System.out.println("An Error has occured");
			
		}

		@Override
		public void onSuccess(Object result) {
			if(result instanceof User)	{
				User user = (User) result;
				//maingui.getLikesFromUser(user);
			}else if(result instanceof Date)	{
				Date date = (Date) result;
				//maingui.getLikesFromUser(date);
			}
		}
	}
	
	/**
	 * Methode die das Abonnement eines Users zurück gibt
	 */
	public void getAbonnementFromPinnwand(Pinnwand p, Date d){
		this.service.getAbonnementFromPinnwand(p, d, new DefaultCallback3());
	}
	
	private class DefaultCallback3 implements AsyncCallback {

		@Override
		public void onFailure(Throwable caught) {
			System.out.println("An Error has occured");
			
		}

		@Override
		public void onSuccess(Object result) {
			if(result instanceof User)	{
				User user = (User) result;
				//maingui.getAbonnementFromPinnwand(user);
			}else if(result instanceof Pinnwand)	{
				Pinnwand pinnwand = (Pinnwand) result;
				//maingui.getAbonnementFromPinnwand(pinnwand);
			}
		}
	}
	
	/**
	 * Methode die den Kommentar eines Beitrags zurück gibt
	 */
	public void getKommentarFromBeitrag(Beitrag b, Date d){
		this.service.getKommentarFromBeitrag(b, d, new DefaultCallback4());
	}
	
	private class DefaultCallback4 implements AsyncCallback {

		@Override
		public void onFailure(Throwable caught) {
			System.out.println("An Error has occured");
			
		}

		@Override
		public void onSuccess(Object result) {
			if(result instanceof Beitrag)	{
				Beitrag beitrag = (Beitrag) beitrag;
				//maingui.getKommentarFromBeitrag(beitrag);
			}else if(result instanceof Date)	{
				Date date = (Date) result;
				//maingui.getKommentarFromBeitrag(date);
			}
		}
	}
	
	/**
	 * Methode die die Likes eines Beitrags zurück gibt
	 */
	public void getLikesFromBeitrag(Beitrag b, Date d){
		this.service.getLikesFromBeitrag(b, d, new DefaultCallback5());
	}
	
	private class DefaultCallback5 implements AsyncCallback {

		@Override
		public void onFailure(Throwable caught) {
			System.out.println("An Error has occured");
			
		}

		@Override
		public void onSuccess(Object result) {
			if(result instanceof Beitrag)	{
				Beitrag beitrag = (Beitrag) beitrag;
				//maingui.getLikesFromBeitrag(beitrag);
			}else if(result instanceof Date)	{
				Date date = (Date) result;
				//maingui.getLikesFromBeitrag(date);
			}
		}
	}


}
