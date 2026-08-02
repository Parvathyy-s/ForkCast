function Navbar() {
  return (
    <nav className="w-full bg-white/80 backdrop-blur-md border-b border-gray-200 sticky top-0 z-50">
      <div className="max-w-7xl mx-auto px-8 py-4 flex justify-between items-center">

        <div>
          <h1 className="text-3xl font-bold text-emerald-600">
            🍽 ForkCast
          </h1>

          <p className="text-sm text-gray-500">
            AI Powered Nutrition Recommendation
          </p>
        </div>

        <div className="hidden md:flex gap-8 text-gray-600 font-medium">
          <a
            href="#home"
            className="hover:text-emerald-600 transition"
          >
            Home
          </a>

          <a
            href="#recommend"
            className="hover:text-emerald-600 transition"
          >
            Recommend
          </a>

          <a
            href="#about"
            className="hover:text-emerald-600 transition"
          >
            About
          </a>
        </div>

      </div>
    </nav>
  );
}

export default Navbar;