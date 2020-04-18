package it.polito.tdp.nobel.model;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import it.polito.tdp.nobel.db.EsameDAO;

public class Model {
	  private int m ;
	  private double bestMedia=0;
	  private Set<Esame> bestSoluzione=null;
	  private List<Esame> esami = new LinkedList<>(); // la recupero dal DB
	  
	  public Model()
	  {
		  EsameDAO dao = new EsameDAO();
		  this.esami=dao.getTuttiEsami();
	  }

	
	public Set<Esame> calcolaSottoinsiemeEsami(int numeroCrediti) {
		  Set<Esame> parziale=new HashSet<>();
		  this.m= numeroCrediti;
		  cerca(parziale,0);
		System.out.println("TODO!");
		
		
		return bestSoluzione;
	}
	
	private void cerca(Set<Esame> parziale, int L) 
	{ //caso terminale
		int crediti = sommaCrediti(parziale);
		if(crediti > m)
			return;
		if(crediti == m)
		{
			double media = calcolaMedia(parziale);
			if(media >bestMedia)
			{
				bestSoluzione= new HashSet<>(parziale);
				bestMedia=media;
			}
			
		} //se arrivo qui vuol dire che crediti < media
		if(L==esami.size())
			return;
		//generare i sottoproblemi
		//esami[L] è da aggiungere o no? Provo entrambre le cose
		
		//provo ad aggiungerlo 
		parziale.add(esami.get(L));
		cerca(parziale,L+1);
		parziale.remove(esami.get(L)); //backtrack
		
		//provo ad  non aggiungerlo 
        cerca(parziale,L+1);
	}


	public double calcolaMedia(Set<Esame> parziale) {
		/*E' media pesata */
		int crediti =0;
		int somma =0;
		
		for(Esame e : parziale)
		{
			crediti += e.getCrediti();
			somma+=(e.getVoto()*e.getCrediti());
		}

		return somma/crediti;
	}


	public int sommaCrediti(Set<Esame> parziale) {
		int somma=0;
		for(Esame s : this.esami)
		{
			somma+=s.getCrediti();
		}
		return somma;
	}

}
