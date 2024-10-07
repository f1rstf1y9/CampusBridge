/** @type {import('tailwindcss').Config} */
export default {
  content: ["./src/**/*.{js,jsx,ts,tsx}"],
  theme: {
    extend: {
      boxShadow: {
        "clay-01":
          "15px 15px 40px 0px rgba(43, 154, 232, 0.5), inset -6px -6px 10px 0px rgba(43, 154, 232, 0.4), inset 0px 6px 14px 0px rgb(255, 255, 255)",
        "clay-02":
          "15px 15px 40px 0px rgba(43, 154, 232, 0.5), inset -6px -6px 10px 0px rgba(43, 131, 232, 0.4), inset 0px 6px 14px 0px rgb(172, 212, 243)",
      },
    },
  },
  plugins: [],
};
