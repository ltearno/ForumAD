package fr.adaming.forum.service;

import fr.adaming.forum.entity.Address;

public interface IAddressService {
	
	public Address addAdresse(Address address);
	public Address getAdresseById(int idAddress);
	public Address updateAdresse(Address address);
	public Address deleteAdresse(int idAddress);

}