// Copyright (c) 2003 Compaq Corporation.  All rights reserved.
// Portions Copyright (c) 2003 Microsoft Corporation.  All rights reserved.
// Last modified on Wed 12 Jul 2017 at 16:10:00 PST by ian morris nieves
//      modified on Mon 30 Apr 2007 at 15:30:08 PST by lamport
//      modified on Thu Feb  8 21:23:55 PST 2001 by yuanyu

package tlc2.value;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import tlc2.tool.ModelChecker;
import tlc2.tool.FingerprintException;
import tla2sany.semantic.SemanticNode;
import tlc2.util.Context;
import util.Assert;

public class LazyValue extends Value {
  /**
   * The field val is the result of evaluating expr in context con and
   * a pair of states.  If val is null, then the value has not been
   * computed, but when computed, the value can be cached in the field
   * val. If val is ValUndef, then the value has not been computed,
   * and when computed, it can not be cached in the field val.
   */

  public SemanticNode expr;
  public Context con;
  public Value val;

  public LazyValue(SemanticNode expr, Context con) {
    this.expr = expr;
    this.con = con;
    this.val = null;
  }

  public final void setUncachable() { this.val = ValUndef; }

  public final byte getKind() { return LAZYVALUE; }

  public final int compareTo(Object obj) {
    try {
      if (this.val == null || this.val == ValUndef) {
        Assert.fail("Error(TLC): Attempted to compare lazy values.");
      }
      return this.val.compareTo(obj);
    }
    catch (RuntimeException | OutOfMemoryError e) {
      if (hasSource()) { throw FingerprintException.getNewHead(this, e); }
      else { throw e; }
    }
  }

  public final boolean equals(Object obj) {
    try {
      if (this.val == null || this.val == ValUndef) {
        Assert.fail("Error(TLC): Attempted to check equality of lazy values.");
      }
      return this.val.equals(obj);
    }
    catch (RuntimeException | OutOfMemoryError e) {
      if (hasSource()) { throw FingerprintException.getNewHead(this, e); }
      else { throw e; }
    }
  }

  public final boolean member(Value elem) {
    try {
      if (this.val == null || this.val == ValUndef) {
        Assert.fail("Error(TLC): Attempted to check set membership of lazy values.");
      }
      return this.val.member(elem);
    }
    catch (RuntimeException | OutOfMemoryError e) {
      if (hasSource()) { throw FingerprintException.getNewHead(this, e); }
      else { throw e; }
    }
  }

  public final boolean isFinite() {
    try {
      if (this.val == null || this.val == ValUndef) {
        Assert.fail("Error(TLC): Attempted to check if a lazy value is a finite set.");
      }
      return this.val.isFinite();
    }
    catch (RuntimeException | OutOfMemoryError e) {
      if (hasSource()) { throw FingerprintException.getNewHead(this, e); }
      else { throw e; }
    }
  }

  public final Value takeExcept(ValueExcept ex) {
    try {
      if (this.val == null || this.val == ValUndef) {
        Assert.fail("Error(TLC): Attempted to apply EXCEPT construct to lazy value.");
      }
      return this.val.takeExcept(ex);
    }
    catch (RuntimeException | OutOfMemoryError e) {
      if (hasSource()) { throw FingerprintException.getNewHead(this, e); }
      else { throw e; }
    }
  }

  public final Value takeExcept(ValueExcept[] exs) {
    try {
      if (this.val == null || this.val == ValUndef) {
        Assert.fail("Error(TLC): Attempted to apply EXCEPT construct to lazy value.");
      }
      return this.val.takeExcept(exs);
    }
    catch (RuntimeException | OutOfMemoryError e) {
      if (hasSource()) { throw FingerprintException.getNewHead(this, e); }
      else { throw e; }
    }
  }

  public final int size() {
    try {
      if (this.val == null || this.val == ValUndef) {
         Assert.fail("Error(TLC): Attempted to compute size of lazy value.");
      }
      return this.val.size();
    }
    catch (RuntimeException | OutOfMemoryError e) {
      if (hasSource()) { throw FingerprintException.getNewHead(this, e); }
      else { throw e; }
    }
  }

  private void readObject(ObjectInputStream ois) throws IOException, ClassNotFoundException {
    this.val = (Value)ois.readObject();
  }

  private void writeObject(ObjectOutputStream oos) throws IOException {
    if (this.val == null || this.val == ValUndef) {
      Assert.fail("Error(TLC): Attempted to serialize lazy value.");
    }
    oos.writeObject(this.val);
  }

  /* Nothing to normalize. */
  public final boolean isNormalized() {
    try {
      if (this.val == null || this.val == ValUndef) {
        Assert.fail("Error(TLC): Attempted to normalize lazy value.");
      }
      return this.val.isNormalized();
    }
    catch (RuntimeException | OutOfMemoryError e) {
      if (hasSource()) { throw FingerprintException.getNewHead(this, e); }
      else { throw e; }
    }
  }

  public final void normalize() {
    try {
      if (this.val == null || this.val == ValUndef) {
        Assert.fail("Error(TLC): Attempted to normalize lazy value.");
      }
      this.val.normalize();
    }
    catch (RuntimeException | OutOfMemoryError e) {
      if (hasSource()) { throw FingerprintException.getNewHead(this, e); }
      else { throw e; }
    }
  }

  public final boolean isDefined() { return true; }

  public final Value deepCopy() {
    try {
      if (this.val == null || this.val == ValUndef) return this;
      return this.val.deepCopy();
    }
    catch (RuntimeException | OutOfMemoryError e) {
      if (hasSource()) { throw FingerprintException.getNewHead(this, e); }
      else { throw e; }
    }
  }

  public final boolean assignable(Value val) {
    try {
      if (this.val == null || this.val == ValUndef) {
        Assert.fail("Error(TLC): Attempted to call assignable on lazy value.");
      }
      return this.val.assignable(val);
    }
    catch (RuntimeException | OutOfMemoryError e) {
      if (hasSource()) { throw FingerprintException.getNewHead(this, e); }
      else { throw e; }
    }
  }

  /* The fingerprint method */
  public final long fingerPrint(long fp) {
    try {
      if (this.val == null || this.val == ValUndef) {
        Assert.fail("Error(TLC): Attempted to fingerprint a lazy value.");
      }
      return this.val.fingerPrint(fp);
    }
    catch (RuntimeException | OutOfMemoryError e) {
      if (hasSource()) { throw FingerprintException.getNewHead(this, e); }
      else { throw e; }
    }
  }

  public final Value permute(MVPerm perm) {
    try {
      if (this.val == null || this.val == ValUndef) {
        Assert.fail("Error(TLC): Attempted to apply permutation to lazy value.");
      }
      return this.val.permute(perm);
    }
    catch (RuntimeException | OutOfMemoryError e) {
      if (hasSource()) { throw FingerprintException.getNewHead(this, e); }
      else { throw e; }
    }
  }

  /* The string representation of the value. */
  public final StringBuffer toString(StringBuffer sb, int offset) {
    try {
      if (this.val == null || this.val == ValUndef) {
        return sb.append("<LAZY " + this.expr + ">");
      }
      return this.val.toString(sb, offset);
    }
    catch (RuntimeException | OutOfMemoryError e) {
      if (hasSource()) { throw FingerprintException.getNewHead(this, e); }
      else { throw e; }
    }
  }

}
