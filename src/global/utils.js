export const addMasks = (e) => {
  e.setAttribute("maxlength", "32");
  return sanitize(e.value);
};

const sanitize = (value) => {
  return value.replace(/[^\p{L}\p{N} ]/gu, "");
};
