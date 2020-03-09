package com.gusi.demo.utils;


import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.impl.crypto.MacProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.security.Key;

/**
 * KeyUtil <br>
 *
 * @author Lucky
 * @since 2020/3/2
 */
public class KeyUtil {
	private static final Logger logger = LoggerFactory.getLogger(KeyUtil.class);

	public static Key getKey() {
//		String path = (context.getRealPath("/key"));
		File file = new File("./", "key.txt");
		ObjectInputStream ois = null;
		try {
			if (!file.exists()) {
				file.createNewFile();
				Key key = MacProvider.generateKey(SignatureAlgorithm.HS512);
				ObjectOutputStream oo = new ObjectOutputStream(new FileOutputStream(file));
				oo.writeObject(key);
				oo.close();
				return key;
			}

			ois = new ObjectInputStream(new FileInputStream(file));

			Key key = (Key) ois.readObject();
			return key;
		} catch (Exception e) {
			logger.debug(e.toString());
			e.printStackTrace();
			return null;
		} finally {
			try {
				if (ois != null)
					ois.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}
}
