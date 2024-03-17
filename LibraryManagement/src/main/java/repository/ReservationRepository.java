package repository;

import entity.Reservation;

import javax.persistence.EntityManager;

public class ReservationRepository {
    private final EntityManager em;

    public ReservationRepository(EntityManager em) {
        this.em = em;
    }

    public String findByIDReservationDate (Integer id){
        try{
            Reservation reservation =  em.find(Reservation.class , id);
            if (reservation == null) {
                return null;
            }
            return reservation.getReservationDate();
        } catch (Exception e){
            System.err.println("something went wrong");
        }
        return null;
    }
}
