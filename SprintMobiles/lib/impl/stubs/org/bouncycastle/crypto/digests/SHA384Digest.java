/**
 * 
 * Message digest classes.
 */
package org.bouncycastle.crypto.digests;


/**
 *  FIPS 180-2 implementation of SHA-384.
 * 
 *  <pre>
 *          block  word  digest
 *  SHA-1   512    32    160
 *  SHA-256 512    32    256
 *  SHA-384 1024   64    384
 *  SHA-512 1024   64    512
 *  </pre>
 */
public class SHA384Digest extends LongDigest {

	/**
	 *  Standard constructor
	 */
	public SHA384Digest() {
	}

	/**
	 *  Copy constructor.  This will copy the state of the provided
	 *  message digest.
	 */
	public SHA384Digest(SHA384Digest t) {
	}

	public String getAlgorithmName() {
	}

	public int getDigestSize() {
	}

	public int doFinal(byte[] out, int outOff) {
	}

	/**
	 *  reset the chaining variables
	 */
	public void reset() {
	}
}
