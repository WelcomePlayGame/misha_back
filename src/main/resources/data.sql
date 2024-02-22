CREATE UNIQUE INDEX idx_product_title ON misha.public.product (lower(title));
CREATE UNIQUE INDEX idx_category_title ON misha.public.category (lower(title));