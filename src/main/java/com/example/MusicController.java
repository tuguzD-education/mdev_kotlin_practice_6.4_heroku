package com.example;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.sql.DataSource;
import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MusicController {
	ArrayList<MusicEntry> ls = new ArrayList<MusicEntry>();

	@RequestMapping(method = RequestMethod.GET, value = "/music")
	public List<MusicEntry> read() {
		return ls;
	}

	@RequestMapping(method = RequestMethod.GET, value = "/music/{id}")
	public MusicEntry read(@PathVariable("id") int id) {
		Iterator<MusicEntry> iter = ls.iterator();
		while (iter.hasNext()) {
			MusicEntry me = iter.next();
			if (me.id == id) {
				return me;
			}
		}
		return null;
	}

	@RequestMapping(method = RequestMethod.PUT, value = "/music")
	public int create(@RequestBody MusicEntry pb) {
		int id=0;
		if(ls.size()>0)
			id=ls.get(ls.size()-1).id+1;
		pb.id=id;
		ls.add(pb);
		return id;
	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/music/{id}")
	public Boolean delete(@PathVariable("id") int id) {
		boolean rez = false;
		Iterator<MusicEntry> iter = ls.iterator();
		while (iter.hasNext()) {
			MusicEntry me = iter.next();
			if (me.id == id) {
				rez = true;
				break;
			}
		}
		if (rez)
			iter.remove();
		return rez;
	}

	@RequestMapping(method = RequestMethod.POST, value = "/music")
	public Boolean update(@RequestBody MusicEntry pb) {
		boolean rez = false;
		Iterator<MusicEntry> iter = ls.iterator();
		while (iter.hasNext()) {
			MusicEntry me = iter.next();
			if (me.id == pb.id) {
				me.name=pb.name;
				me.album=pb.album;
				rez = true;
				break;
			}
		}
		return rez;
	}

}
