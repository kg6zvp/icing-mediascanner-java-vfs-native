package enterprises.mccollum.home.icing_legacy.mediascanner;

import java.io.File;
import java.util.LinkedList;
import java.util.List;

import enterprises.mccollum.icing.tmdb.filesystem.VFSNode;
import enterprises.mccollum.icing.tmdb.required.VFSBrowser;

/**
 * Created by smccollum on 15.12.17.
 */
public class NativeFileBrowser implements VFSBrowser {
	@Override
	public VFSNode createVFSNode(String path) {
		VFSNode n = new VFSNode();
		File f = new File(path);
		n.setName(f.getName());
		n.setType(VFSNode.Type.PATH);
		return n;
	}
	
	@Override
	public List<VFSNode> browseDir(String dir) {
		return browseDir(createVFSNode(dir));
	}
	
	@Override
	public List<VFSNode> browseDir(VFSNode dir) {
		File browse = new File(dir.getCompletePath());
		if(!browse.exists())
			return new LinkedList<>();
		List<VFSNode> contains = new LinkedList<>();
		for(File current : browse.listFiles()){
			VFSNode n = new VFSNode(dir.getSeparator());
			n.setName(current.getName());
			n.setParent(dir);
			n.setType(current.isDirectory() ? VFSNode.Type.DIRECTORY : VFSNode.Type.FILE);
			contains.add(n);
		}
		return contains;
	}
	
	@Override
	public List<VFSNode> browseRelative(VFSNode parent, VFSNode child) {
		return null;
	}
}
