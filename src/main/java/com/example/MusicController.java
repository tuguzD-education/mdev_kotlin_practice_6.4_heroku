package com.example;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MusicController {
	ArrayList<MusicEntry> ls = new ArrayList<>();

	@RequestMapping(method = RequestMethod.PUT, value = "/music")
	public int create(@RequestBody MusicEntry pb) {
		if (pb.id == 0 || ls.stream().anyMatch(obj -> obj.id == pb.id)) {
			if (!ls.isEmpty())
				pb.id = ls.get(ls.size() - 1).id + 1;
		}
		ls.add(pb);

		return pb.id;
	}

	@RequestMapping(method = RequestMethod.GET, value = "/music/{id}")
	public MusicEntry read(@PathVariable("id") int id) {
		return ls.stream().filter(obj -> obj.id == id).findAny().orElse(null);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/music")
	public Boolean update(@RequestBody MusicEntry pb) {
		for (MusicEntry me : ls) {
			if (me.id == pb.id) {
				me.name = pb.name;
				me.album = pb.album;
				return true;
			}
		}
		return false;
	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/music/{id}")
	public Boolean delete(@PathVariable("id") int id) {
		Iterator<MusicEntry> iter = ls.iterator();

		while (iter.hasNext()) {
			MusicEntry me = iter.next();
			if (me.id == id) {
				iter.remove();
				return true;
			}
		}
		return false;
	}

	@RequestMapping(method = RequestMethod.GET, value = "/music")
	public List<MusicEntry> read() {
		return ls;
	}
}
