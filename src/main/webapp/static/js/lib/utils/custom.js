// javascript extend for prototype inheritance.
if (!Function.prototype.extend) {
  Function.prototype.extend = function(SuperClass) {
    function F(){}
    F.prototype = SuperClass.prototype;
    this.prototype = new F();
    this.prototype.constructor = this; 
  }
}